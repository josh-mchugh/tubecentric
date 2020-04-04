package com.tubecentric.webapplication.framework.security.oauth2;

import com.tubecentric.webapplication.framework.security.google.GoogleRefreshTokenRequest;
import com.tubecentric.webapplication.framework.security.google.GoogleRefreshTokenResponse;
import com.tubecentric.webapplication.user.model.UserAccessToken;
import com.tubecentric.webapplication.user.service.IUserAccessTokenService;
import com.tubecentric.webapplication.user.service.model.UserAccessTokenUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthorizedClientService implements OAuth2AuthorizedClientService {

    private final IUserAccessTokenService userAccessTokenService;
    private final ClientRegistrationRepository clientRegistrationRepository;

    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String principalName) {

        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(clientRegistrationId);

        UserAccessToken userAccessToken = userAccessTokenService.findBySub(principalName);

        if(userAccessToken.getAccessTokenExpiresAt().isAfter(Instant.now())) {

            return loadOAuth2AuthorizedClient(clientRegistration, principalName, userAccessToken);
        }

        return loadUpdatedOAuth2AuthorizedClient(clientRegistration, principalName, userAccessToken);
    }

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {

        userAccessTokenService.updateOrCreateAccessToken(authorizedClient);
    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {

        userAccessTokenService.deleteBySub(principalName);
    }

    private <T extends OAuth2AuthorizedClient> T loadOAuth2AuthorizedClient(ClientRegistration clientRegistration, String principalName, UserAccessToken userAccessToken) {

        OAuth2RefreshToken refreshToken = getRefreshToken(userAccessToken);
        OAuth2AccessToken accessToken = getAccessToken(userAccessToken);

        return (T) new OAuth2AuthorizedClient(clientRegistration, principalName, accessToken, refreshToken);
    }

    private <T extends OAuth2AuthorizedClient> T loadUpdatedOAuth2AuthorizedClient(ClientRegistration clientRegistration, String principalName, UserAccessToken userAccessToken) {

        OAuth2RefreshToken refreshToken = getRefreshToken(userAccessToken);
        OAuth2AccessToken accessToken = getNewAccessToken(clientRegistration, userAccessToken);

        UserAccessTokenUpdateRequest request = UserAccessTokenUpdateRequest.builder()
                .id(userAccessToken.getId())
                .accessToken(accessToken)
                .build();

        userAccessTokenService.handleUpdateAccessToken(request);

        return (T) new OAuth2AuthorizedClient(clientRegistration, principalName, accessToken, refreshToken);
    }

    private OAuth2RefreshToken getRefreshToken(UserAccessToken userAccessToken) {

        String tokenValue = userAccessToken.getRefreshTokenValue();
        Instant issuedAt = userAccessToken.getRefreshTokenIssuedAt();

        return new OAuth2RefreshToken(tokenValue, issuedAt);
    }

    private OAuth2AccessToken getAccessToken(UserAccessToken userAccessToken) {

        OAuth2AccessToken.TokenType tokenType = OAuth2AccessToken.TokenType.BEARER;
        String tokenValue = userAccessToken.getAccessTokenValue();
        Instant issuedAt = userAccessToken.getAccessTokenIssuedAt();
        Instant expiresAt = userAccessToken.getAccessTokenExpiresAt();
        Set<String> scope = userAccessToken.getScopes();

        return new OAuth2AccessToken(tokenType, tokenValue, issuedAt, expiresAt, scope);
    }

    private OAuth2AccessToken getNewAccessToken(ClientRegistration clientRegistration, UserAccessToken userAccessToken) {

        Instant issuedAt = Instant.now();

        GoogleRefreshTokenRequest request = GoogleRefreshTokenRequest.builder()
                .clientId(clientRegistration.getClientId())
                .clientSecret(clientRegistration.getClientSecret())
                .grantType("refresh_token")
                .refreshToken(userAccessToken.getRefreshTokenValue())
                .build();

        GoogleRefreshTokenResponse response = getNewRefreshToken(request);

        Instant expiresAt = issuedAt.plusSeconds(response.getExpiresIn());
        Set<String> scopes = userAccessToken.getScopes();

        return new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, response.getAccessToken(), issuedAt, expiresAt, scopes);
    }

    private GoogleRefreshTokenResponse getNewRefreshToken(GoogleRefreshTokenRequest request) {

        String url = UriComponentsBuilder.fromHttpUrl("https://www.googleapis.com/")
                .path("/oauth2/v4/token")
                .toUriString();

        return new RestTemplate().postForObject(url, request, GoogleRefreshTokenResponse.class);
    }
}

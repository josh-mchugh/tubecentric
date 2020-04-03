package com.tubecentric.webapplication.framework.security.oauth2;

import com.tubecentric.webapplication.framework.security.google.GoogleRefreshTokenRequest;
import com.tubecentric.webapplication.framework.security.google.GoogleRefreshTokenResponse;
import com.tubecentric.webapplication.user.IUserAccessTokenService;
import com.tubecentric.webapplication.user.entity.UserAccessTokenEntity;
import com.tubecentric.webapplication.user.entity.UserAccessTokenScopeEntity;
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
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthorizedClientService implements OAuth2AuthorizedClientService {

    private final IUserAccessTokenService userAccessTokenService;
    private final ClientRegistrationRepository clientRegistrationRepository;

    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String principalName) {

        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(clientRegistrationId);

        UserAccessTokenEntity userAccessTokenEntity = userAccessTokenService.findBySub(principalName);

        if(userAccessTokenEntity.getAccessTokenExpiresAt().isAfter(Instant.now())) {

            OAuth2RefreshToken refreshToken = getRefreshToken(userAccessTokenEntity);
            OAuth2AccessToken accessToken = getAccessToken(userAccessTokenEntity);

            return (T) new OAuth2AuthorizedClient(clientRegistration, principalName, accessToken, refreshToken);
        }

        OAuth2RefreshToken refreshToken = getRefreshToken(userAccessTokenEntity);
        OAuth2AccessToken accessToken = getNewAccessToken(clientRegistration, userAccessTokenEntity);

        userAccessTokenService.handleUpdateAccessToken(userAccessTokenEntity, accessToken);

        return (T) new OAuth2AuthorizedClient(clientRegistration, principalName, accessToken, refreshToken);
    }

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {

        userAccessTokenService.updateOrCreateAccessToken(principal, authorizedClient);
    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {

        userAccessTokenService.deleteBySub(principalName);
    }

    private OAuth2RefreshToken getRefreshToken(UserAccessTokenEntity userAccessTokenEntity) {

        String tokenValue = userAccessTokenEntity.getRefreshTokenValue();
        Instant issuedAt = userAccessTokenEntity.getRefreshTokenIssuedAt();

        return new OAuth2RefreshToken(tokenValue, issuedAt);
    }

    private OAuth2AccessToken getAccessToken(UserAccessTokenEntity userAccessTokenEntity) {

        OAuth2AccessToken.TokenType tokenType = OAuth2AccessToken.TokenType.BEARER;
        String tokenValue = userAccessTokenEntity.getAccessTokenValue();
        Instant issuedAt = userAccessTokenEntity.getAccessTokenIssuedAt();
        Instant expiresAt = userAccessTokenEntity.getAccessTokenExpiresAt();
        Set<String> scope = getScopes(userAccessTokenEntity);

        return new OAuth2AccessToken(tokenType, tokenValue, issuedAt, expiresAt, scope);
    }

    private Set<String> getScopes(UserAccessTokenEntity userAccessTokenEntity) {

        return userAccessTokenEntity.getScopes().stream()
                .map(UserAccessTokenScopeEntity::getScope)
                .collect(Collectors.toSet());
    }

    private OAuth2AccessToken getNewAccessToken(ClientRegistration clientRegistration, UserAccessTokenEntity userAccessTokenEntity) {

        Instant issuedAt = Instant.now();

        GoogleRefreshTokenRequest request = GoogleRefreshTokenRequest.builder()
                .clientId(clientRegistration.getClientId())
                .clientSecret(clientRegistration.getClientSecret())
                .grantType("refresh_token")
                .refreshToken(userAccessTokenEntity.getRefreshTokenValue())
                .build();

        GoogleRefreshTokenResponse response = getNewRefreshToken(request);

        Instant expiresAt = issuedAt.plusSeconds(response.getExpiresIn());
        Set<String> scopes = getScopes(userAccessTokenEntity);

        return new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, response.getAccessToken(), issuedAt, expiresAt, scopes);
    }

    private GoogleRefreshTokenResponse getNewRefreshToken(GoogleRefreshTokenRequest request) {

        String url = UriComponentsBuilder.fromHttpUrl("https://www.googleapis.com/")
                .path("/oauth2/v4/token")
                .toUriString();

        return new RestTemplate().postForObject(url, request, GoogleRefreshTokenResponse.class);
    }
}

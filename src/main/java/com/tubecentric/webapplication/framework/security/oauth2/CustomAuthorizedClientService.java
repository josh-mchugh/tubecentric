package com.tubecentric.webapplication.framework.security.oauth2;

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

        OAuth2RefreshToken refreshToken = getRefreshToken(userAccessTokenEntity);
        OAuth2AccessToken accessToken = getAccessToken(userAccessTokenEntity);

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

        Set<String> scope = userAccessTokenEntity.getScopes().stream()
                .map(UserAccessTokenScopeEntity::getScope)
                .collect(Collectors.toSet());

        return new OAuth2AccessToken(tokenType, tokenValue, issuedAt, expiresAt, scope);
    }
}

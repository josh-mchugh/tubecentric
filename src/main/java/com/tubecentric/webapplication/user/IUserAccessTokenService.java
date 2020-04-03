package com.tubecentric.webapplication.user;

import com.tubecentric.webapplication.user.entity.UserAccessTokenEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import java.security.Principal;

public interface IUserAccessTokenService {

    UserAccessTokenEntity findBySub(String sub);

    boolean existsBySub(String sub);

    UserAccessTokenEntity updateOrCreateAccessToken(Principal principal, OAuth2AuthorizedClient authorizedClient);

    void deleteBySub(String sub);

    UserAccessTokenEntity handleUpdateAccessToken(UserAccessTokenEntity userAccessTokenEntity, OAuth2AccessToken accessToken);
}

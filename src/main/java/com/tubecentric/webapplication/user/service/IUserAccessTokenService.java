package com.tubecentric.webapplication.user.service;

import com.tubecentric.webapplication.user.model.UserAccessToken;
import com.tubecentric.webapplication.user.service.model.UserAccessTokenUpdateRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

public interface IUserAccessTokenService {

    UserAccessToken findBySub(String sub);

    boolean existsBySub(String sub);

    UserAccessToken updateOrCreateAccessToken(OAuth2AuthorizedClient authorizedClient);

    void deleteBySub(String sub);

    UserAccessToken handleUpdateAccessToken(UserAccessTokenUpdateRequest request);
}

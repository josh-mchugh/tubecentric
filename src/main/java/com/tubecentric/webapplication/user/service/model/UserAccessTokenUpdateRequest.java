package com.tubecentric.webapplication.user.service.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

@Value
@Builder(builderClassName = "Builder")
public class UserAccessTokenUpdateRequest {

    private final String id;
    private final OAuth2AccessToken accessToken;
}

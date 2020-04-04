package com.tubecentric.webapplication.framework.security.oauth2;

import com.tubecentric.webapplication.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomOidcUserService extends OidcUserService {

    private final IUserService userService;

    @Override
    public OidcUser loadUser(OidcUserRequest oidcUserRequest) throws OAuth2AuthenticationException {

        OidcUser oidcUser = super.loadUser(oidcUserRequest);

        userService.updateOrCreateUser(oidcUser);

        return oidcUser;
    }
}

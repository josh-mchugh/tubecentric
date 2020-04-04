package com.tubecentric.webapplication.user.service;

import com.tubecentric.webapplication.user.model.User;
import com.tubecentric.webapplication.user.service.model.AuthenticationResponse;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public interface IUserService {

    User findBySub(String sub);

    boolean existsBySub(String sub);

    User updateOrCreateUser(OidcUser oidcUser);

    AuthenticationResponse handleAuthenticationRequest(String sub);
}

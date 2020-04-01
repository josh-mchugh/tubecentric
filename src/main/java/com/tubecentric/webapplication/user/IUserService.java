package com.tubecentric.webapplication.user;

import com.tubecentric.webapplication.user.entity.UserEntity;
import com.tubecentric.webapplication.user.model.AuthenticationResponse;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public interface IUserService {

    UserEntity findBySub(String sub);

    boolean existsBySub(String sub);

    UserEntity updateOrCreateUser(OidcUser oidcUser);

    AuthenticationResponse handleAuthenticationRequest(String sub);
}

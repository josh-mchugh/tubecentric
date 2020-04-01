package com.tubecentric.webapplication.framework.security.oauth2;

import com.tubecentric.webapplication.framework.security.cookie.CookieUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        CookieUtils.deleteCookie(request, response, CookieUtils.AUTHORIZATION_REQUEST_COOKIE_NAME);

        super.onAuthenticationFailure(request, response, exception);
    }
}

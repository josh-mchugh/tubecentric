package com.tubecentric.webapplication.framework.security.oauth2;

import com.tubecentric.webapplication.framework.config.AppConfig;
import com.tubecentric.webapplication.framework.security.cookie.CookieUtils;
import com.tubecentric.webapplication.framework.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AppConfig appConfig;
    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        if(response.isCommitted()) {

            return;
        }

        if(authentication != null) {

            addJwtTokenCookie(response, authentication);
        }

        CookieUtils.deleteCookie(request, response, CookieUtils.AUTHORIZATION_REQUEST_COOKIE_NAME);

        super.onAuthenticationSuccess(request, response, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

        if(response.isCommitted()) {

            return;
        }

        if(authentication != null) {

            addJwtTokenCookie(response, authentication);
        }

        CookieUtils.deleteCookie(request, response, CookieUtils.AUTHORIZATION_REQUEST_COOKIE_NAME);

        this.onAuthenticationSuccess(request, response, chain, authentication);
    }

    private void addJwtTokenCookie(HttpServletResponse response, Authentication authentication) {

        String jwtToken = tokenProvider.createToken(authentication);
        Integer expiry = (int) TimeUnit.DAYS.toSeconds(appConfig.getJwt().getExpiresInDays());

        CookieUtils.addCookie(response, CookieUtils.JWT_COOKIE_NAME, jwtToken, expiry);
    }
}

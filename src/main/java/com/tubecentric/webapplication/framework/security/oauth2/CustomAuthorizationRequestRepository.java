package com.tubecentric.webapplication.framework.security.oauth2;

import com.tubecentric.webapplication.framework.config.AppConfig;
import com.tubecentric.webapplication.framework.security.cookie.CookieUtils;
import com.tubecentric.webapplication.framework.security.cookie.model.CookieAddRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class CustomAuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    private final AppConfig appConfig;

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {

        return CookieUtils.getCookie(request, CookieUtils.AUTHORIZATION_REQUEST_COOKIE_NAME)
                .map(Cookie::getValue)
                .map(value -> Base64.getUrlDecoder().decode(value))
                .map(bytes -> (OAuth2AuthorizationRequest) SerializationUtils.deserialize(bytes))
                .orElse(null);
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {

        if (authorizationRequest == null) {

            CookieUtils.deleteCookie(request, response, CookieUtils.AUTHORIZATION_REQUEST_COOKIE_NAME);

            return;
        }

        CookieAddRequest addRequest = CookieAddRequest.builder()
                .response(response)
                .name(CookieUtils.AUTHORIZATION_REQUEST_COOKIE_NAME)
                .value(Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(authorizationRequest)))
                .expiry(180)
                .httpOnly(appConfig.getJwt().getCookie().isHttpOnly())
                .secure(appConfig.getJwt().getCookie().isSecure())
                .build();

        CookieUtils.addCookie(addRequest);
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {

        return this.loadAuthorizationRequest(request);
    }
}

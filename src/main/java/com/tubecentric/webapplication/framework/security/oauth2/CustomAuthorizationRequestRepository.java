package com.tubecentric.webapplication.framework.security.oauth2;

import com.tubecentric.webapplication.framework.security.cookie.CookieUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@Component
public class CustomAuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

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

        String serializedRequest = Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(authorizationRequest));
        CookieUtils.addCookie(response, CookieUtils.AUTHORIZATION_REQUEST_COOKIE_NAME, serializedRequest, 180);
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {

        return this.loadAuthorizationRequest(request);
    }
}

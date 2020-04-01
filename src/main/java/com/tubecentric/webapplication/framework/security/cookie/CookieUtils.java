package com.tubecentric.webapplication.framework.security.cookie;

import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class CookieUtils {

    public static final String AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
    public static final String JWT_COOKIE_NAME = "token";

    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {

        return Optional.ofNullable(WebUtils.getCookie(request, name));
    }

    public static void addCookie(HttpServletResponse response, String name, String value, Integer expiry) {

        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(expiry);

        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {

        for(Cookie cookie : request.getCookies()) {

            if(cookie.getName().equals(name)) {

                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);

                response.addCookie(cookie);
            }
        }
    }
}

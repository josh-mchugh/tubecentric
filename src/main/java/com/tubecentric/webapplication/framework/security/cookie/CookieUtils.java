package com.tubecentric.webapplication.framework.security.cookie;

import com.tubecentric.webapplication.framework.security.cookie.model.CookieAddRequest;
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

    public static void addCookie(CookieAddRequest request) {

        Cookie cookie = new Cookie(request.getName(), request.getValue());
        cookie.setPath(request.getPath());
        cookie.setHttpOnly(request.isHttpOnly());
        cookie.setSecure(request.isSecure());
        cookie.setMaxAge(request.getExpiry());

        request.getResponse().addCookie(cookie);
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

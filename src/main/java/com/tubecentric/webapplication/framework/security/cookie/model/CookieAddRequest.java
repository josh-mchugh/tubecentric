package com.tubecentric.webapplication.framework.security.cookie.model;

import lombok.Builder;
import lombok.Value;

import javax.servlet.http.HttpServletResponse;

@Value
@Builder(builderClassName = "Builder")
public class CookieAddRequest {

    private final HttpServletResponse response;
    private final String name;
    private final String value;
    private final Integer expiry;

    @lombok.Builder.Default
    private final String path = "/";

    @lombok.Builder.Default
    private final boolean httpOnly = true;

    @lombok.Builder.Default
    private final boolean secure = false;
}

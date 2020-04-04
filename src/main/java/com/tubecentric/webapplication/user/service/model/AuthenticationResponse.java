package com.tubecentric.webapplication.user.service.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Map;

@Value
@Builder(builderClassName = "Builder")
public class AuthenticationResponse {

    private final List<SimpleGrantedAuthority> authorities;
    private final Map<String, Object> attributes;
}

package com.tubecentric.webapplication.user.model;

import com.google.common.collect.ImmutableSet;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.time.Instant;

@Value
@Builder(builderClassName = "Builder")
public class UserAccessToken {

    private final String id;
    private final String refreshTokenValue;
    private final Instant refreshTokenIssuedAt;
    private final String accessTokenType;
    private final String accessTokenValue;
    private final Instant accessTokenIssuedAt;
    private final Instant accessTokenExpiresAt;

    @Singular
    private final ImmutableSet<String> scopes;
}

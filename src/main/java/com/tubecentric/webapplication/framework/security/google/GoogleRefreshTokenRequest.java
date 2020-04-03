package com.tubecentric.webapplication.framework.security.google;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
@JsonDeserialize(builder = GoogleRefreshTokenRequest.Builder.class)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class GoogleRefreshTokenRequest {

    @JsonProperty("client_id")
    private final String clientId;

    @JsonProperty("client_secret")
    private final String clientSecret;

    @JsonProperty("refresh_token")
    private final String refreshToken;

    @JsonProperty("grant_type")
    private final String grantType;

    @JsonPOJOBuilder(withPrefix="")
    public static class Builder {
        // Lombok will add constructor, setters, build method
    }
}

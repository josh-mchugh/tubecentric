package com.tubecentric.webapplication.framework.security.google;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
@JsonDeserialize(builder = GoogleRefreshTokenResponse.Builder.class)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class GoogleRefreshTokenResponse {

    @JsonProperty("access_token")
    private final String accessToken;

    @JsonProperty("expires_in")
    private final Long expiresIn;

    private final String scope;

    @JsonProperty("token_type")
    private final String tokenType;

    @JsonPOJOBuilder(withPrefix="")
    public static class Builder {
        // Lombok will add constructor, setters, build method
    }
}

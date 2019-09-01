package com.tubecentric.webapplication.search.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder")
@JsonDeserialize(builder= KeywordSearchDefaultRequest.Builder.class)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class KeywordSearchDefaultRequest {

    @lombok.Builder.Default
    private final String query = "";

    @JsonPOJOBuilder(withPrefix="")
    public static class Builder {
        // Lombok will add constructor, setters, build method
    }
}

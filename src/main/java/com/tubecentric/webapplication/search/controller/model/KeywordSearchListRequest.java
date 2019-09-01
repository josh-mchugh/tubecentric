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
@JsonDeserialize(builder= KeywordSearchListRequest.Builder.class)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class KeywordSearchListRequest {

    @lombok.Builder.Default
    private final String query = "";

    @lombok.Builder.Default
    private final int startIndex = 0;

    @lombok.Builder.Default
    private final int numberResults = 20;

    @JsonPOJOBuilder(withPrefix="")
    public static class Builder {
        // Lombok will add constructor, setters, build method
    }
}

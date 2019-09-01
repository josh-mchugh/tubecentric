package com.tubecentric.webapplication.search.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.tubecentric.webapplication.search.model.Keyword;
import lombok.Builder;
import lombok.Value;

import java.util.Collection;

@Value
@Builder(builderClassName = "Builder")
@JsonDeserialize(builder=KeywordSearchListResponse.Builder.class)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class KeywordSearchListResponse {

    private final Collection<Keyword> data;

    @JsonPOJOBuilder(withPrefix="")
    public static class Builder {
        // Lombok will add constructor, setters, build method
    }
}

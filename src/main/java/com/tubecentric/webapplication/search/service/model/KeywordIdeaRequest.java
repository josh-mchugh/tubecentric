package com.tubecentric.webapplication.search.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class KeywordIdeaRequest {

    private final String query;
    private final int startIndex;
    private final int numberResults;
}

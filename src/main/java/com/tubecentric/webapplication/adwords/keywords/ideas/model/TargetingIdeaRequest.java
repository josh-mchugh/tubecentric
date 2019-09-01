package com.tubecentric.webapplication.adwords.keywords.ideas.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName="Builder")
public class TargetingIdeaRequest {

    private final String query;
    private final int startIndex;
    private final int numberResults;
}

package com.tubecentric.webapplication.adwords.keywords.ideas.model;

import com.google.api.ads.adwords.axis.v201809.o.TargetingIdea;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class TargetingIdeaResponse {

    private final TargetingIdea[] keywordIdeas;
}

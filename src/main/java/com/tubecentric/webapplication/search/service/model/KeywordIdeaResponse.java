package com.tubecentric.webapplication.search.service.model;

import com.tubecentric.webapplication.search.model.Keyword;
import lombok.Builder;
import lombok.Value;

import java.util.Collection;

@Value
@Builder(builderClassName = "Builder")
public class KeywordIdeaResponse {

    private final Collection<Keyword> keywords;
}

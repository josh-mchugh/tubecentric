package com.tubecentric.webapplication.search.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class Keyword {

    private final String keyword;
    private final Long searchVolume;
    private final Long competition;
    private final KeywordCompetitionRating competitionRating;
}

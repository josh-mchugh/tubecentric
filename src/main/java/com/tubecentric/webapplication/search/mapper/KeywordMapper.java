package com.tubecentric.webapplication.search.mapper;

import com.google.api.ads.adwords.axis.v201809.o.Attribute;
import com.google.api.ads.adwords.axis.v201809.o.AttributeType;
import com.google.api.ads.adwords.axis.v201809.o.DoubleAttribute;
import com.google.api.ads.adwords.axis.v201809.o.LongAttribute;
import com.google.api.ads.adwords.axis.v201809.o.StringAttribute;
import com.google.api.ads.adwords.axis.v201809.o.TargetingIdea;
import com.google.api.ads.common.lib.utils.Maps;
import com.tubecentric.webapplication.search.model.Keyword;
import com.tubecentric.webapplication.search.model.KeywordCompetitionRating;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KeywordMapper {

    public static Keyword map(TargetingIdea targetingIdea) {

        Map<AttributeType, Attribute> data = Maps.toMap(targetingIdea.getData());

        Long competition = getCompetition(data);

        return Keyword.builder()
                .keyword(convertToString(data, AttributeType.KEYWORD_TEXT))
                .searchVolume(convertToLong(data, AttributeType.SEARCH_VOLUME))
                .competition(competition)
                .competitionRating(getRecommendationRating(competition))
                .build();
    }

    public static List<Keyword> map(TargetingIdea[] targetingIdeas) {

        List<Keyword> keywordIdeas = new ArrayList<>();

        for(TargetingIdea targetingIdea : targetingIdeas) {

            keywordIdeas.add(map(targetingIdea));
        }

        return keywordIdeas;
    }

    private static Long convertToLong(Map<AttributeType, Attribute> data, AttributeType type) {

        return ((LongAttribute) data.get(type)).getValue();
    }

    private static String convertToString(Map<AttributeType, Attribute> data, AttributeType type) {

        return ((StringAttribute) data.get(type)).getValue();
    }

    private static Double convertToDouble(Map<AttributeType, Attribute> data, AttributeType type) {

        return ((DoubleAttribute) data.get(type)).getValue();
    }

    private static Long getCompetition(Map<AttributeType, Attribute> data) {

        return Math.round(convertToDouble(data, AttributeType.COMPETITION) * 100d);
    }

    private static KeywordCompetitionRating getRecommendationRating(Long competition) {

       if(competition < 33) {

           return KeywordCompetitionRating.LOW;

       }else if (competition < 66) {

           return KeywordCompetitionRating.MEDIUM;
       }

       return KeywordCompetitionRating.HIGH;
    }
}

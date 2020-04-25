package com.tubecentric.webapplication.web.search.model;

import com.google.common.collect.ImmutableList;
import com.tubecentric.webapplication.web.search.service.RelatedTagsV2Service;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RankedTags {

    private String query;

    @Singular
    private List<String> words;

    @Singular
    private List<RankedTag> rankedTags;
}
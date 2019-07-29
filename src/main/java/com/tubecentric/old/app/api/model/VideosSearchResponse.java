package com.tubecentric.old.app.api.model;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class VideosSearchResponse {

    private final String query;

    private final String regionCode;

    private final String nextPageToken;
    private final boolean hasNextPage;
    private final String prevPageToken;
    private final boolean hasPrevPage;

    private final Long totalResults;
    private final Long resultsPerPage;

    @Singular
    private List<SearchVideo> videos;
}

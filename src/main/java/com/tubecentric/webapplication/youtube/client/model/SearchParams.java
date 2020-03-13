package com.tubecentric.webapplication.youtube.client.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName="Builder")
public class SearchParams {

    private final String q;
    private final String key;

    @lombok.Builder.Default
    private final String hl = "en";

    @lombok.Builder.Default
    private final int maxResults = 20;

    @lombok.Builder.Default
    private final String part = "id,snippet";

    @lombok.Builder.Default
    private final String fields = "items(id(videoId))";
}

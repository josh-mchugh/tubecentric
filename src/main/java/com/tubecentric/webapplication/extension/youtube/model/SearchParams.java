package com.tubecentric.webapplication.extension.youtube.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName="Builder")
public class SearchParams {

    private String q;
    private String key;

    @lombok.Builder.Default
    private String hl = "en";

    @lombok.Builder.Default
    private int maxResults = 20;

    @lombok.Builder.Default
    private String part = "id,snippet";

    @lombok.Builder.Default
    private String fields = "items(id(videoId))";
}

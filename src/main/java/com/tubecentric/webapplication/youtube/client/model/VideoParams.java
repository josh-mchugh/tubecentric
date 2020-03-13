package com.tubecentric.webapplication.youtube.client.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName="Builder")
public class VideoParams {

    private final String id;
    private final String key;

    @lombok.Builder.Default
    private final String hl = "en";

    @lombok.Builder.Default
    private final String part = "snippet";

    @lombok.Builder.Default
    private final String fields = "items(snippet(tags))";
}

package com.tubecentric.webapplication.extension.youtube.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName="Builder")
public class VideoParams {

    private String id;
    private String key;

    @lombok.Builder.Default
    private String hl = "en";

    @lombok.Builder.Default
    private String part = "snippet";

    @lombok.Builder.Default
    private String fields = "items(snippet(tags))";
}

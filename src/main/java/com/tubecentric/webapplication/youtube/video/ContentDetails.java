package com.tubecentric.webapplication.youtube.video;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContentDetails {

    @JsonProperty("duration")
    private String duration;

    @JsonProperty("dimension")
    private String dimension;

    @JsonProperty("definition")
    private String definition;

    @JsonProperty("caption")
    private String caption;

    @JsonProperty("licensedContent")
    private Boolean licensedContent;

    @JsonProperty("projection")
    private String projection;

    @JsonProperty("hasCustomThumbnail")
    private Boolean customThumbnail;
}
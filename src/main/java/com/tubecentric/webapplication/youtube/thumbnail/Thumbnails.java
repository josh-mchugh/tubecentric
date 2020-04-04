package com.tubecentric.webapplication.youtube.thumbnail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Thumbnails {

    @JsonProperty("default")
    private Thumbnail _default;

    @JsonProperty("medium")
    private Thumbnail medium;

    @JsonProperty("high")
    private Thumbnail high;

    @JsonProperty("standard")
    private Thumbnail standard;

    @JsonProperty("maxres")
    private Thumbnail max;
}
package com.tubecentric.webapplication.youtube.thumbnail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Standard {

    @JsonProperty("url")
    private String url;

    @JsonProperty("width")
    private int width;

    @JsonProperty("height")
    private int height;
}
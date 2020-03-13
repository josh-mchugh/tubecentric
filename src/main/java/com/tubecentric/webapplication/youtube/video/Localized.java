package com.tubecentric.webapplication.youtube.video;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Localized {

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;
}
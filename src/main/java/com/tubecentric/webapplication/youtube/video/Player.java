package com.tubecentric.webapplication.youtube.video;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Player {

    @JsonProperty("embedHtml")
    private String embedHtml;
}
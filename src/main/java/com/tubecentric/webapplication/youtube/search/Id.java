package com.tubecentric.webapplication.youtube.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Id {

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("videoId")
    private String videoId;

    @JsonProperty("channelId")
    private String channelId;
}
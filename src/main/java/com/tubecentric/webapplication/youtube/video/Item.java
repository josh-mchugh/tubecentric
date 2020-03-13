package com.tubecentric.webapplication.youtube.video;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Item {

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("etag")
    private String etag;

    @JsonProperty("id")
    private String id;

    @JsonProperty("snippet")
    private Snippet snippet;

    @JsonProperty("contentDetails")
    private ContentDetails contentDetails;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("statistics")
    private Statistics statistics;

    @JsonProperty("player")
    private Player player;

    @JsonProperty("topicDetails")
    private TopicDetails topicDetails;
}
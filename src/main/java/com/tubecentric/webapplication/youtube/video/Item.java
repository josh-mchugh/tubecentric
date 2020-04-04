package com.tubecentric.webapplication.youtube.video;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Item {

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

    @JsonProperty("topicDetails")
    private TopicDetails topicDetails;
}
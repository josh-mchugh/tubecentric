package com.tubecentric.webapplication.youtube.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tubecentric.webapplication.youtube.thumbnail.Thumbnails;

import java.time.LocalDateTime;

public class Snippet {

    @JsonProperty("publishedAt")
    private LocalDateTime publishedAt;

    @JsonProperty("channelId")
    private String channelId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("thumbnails")
    private Thumbnails thumbnails;

    @JsonProperty("channelTitle")
    private String channelTitle;

    @JsonProperty("liveBroadcastContent")
    private String liveBroadcastContent;
}
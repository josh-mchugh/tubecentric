package com.tubecentric.webapplication.youtube.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tubecentric.webapplication.youtube.thumbnail.Thumbnails;

public class Snippet {

    @JsonProperty("publishedAt")
    private String publishedAt;

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
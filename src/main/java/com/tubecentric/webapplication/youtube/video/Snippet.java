package com.tubecentric.webapplication.youtube.video;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tubecentric.webapplication.youtube.thumbnail.Thumbnails;
import lombok.Data;

import java.util.List;

@Data
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

    @JsonProperty("tags")
    private List<String> tags;

    @JsonProperty("categoryId")
    private String categoryId;

    @JsonProperty("liveBroadcastContent")
    private String liveBroadcastContent;

    @JsonProperty("defaultLanguage")
    private String defaultLanguage;

    @JsonProperty("localized")
    private Localized localized;

    @JsonProperty("defaultAudioLanguage")
    private String defaultAudioLanguage;
}
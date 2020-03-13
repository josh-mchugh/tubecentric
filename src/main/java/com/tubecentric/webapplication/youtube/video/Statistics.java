package com.tubecentric.webapplication.youtube.video;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Statistics {

    @JsonProperty("viewCount")
    private Long viewCount;

    @JsonProperty("likeCount")
    private Long likeCount;

    @JsonProperty("dislikeCount")
    private Long dislikeCount;

    @JsonProperty("favoriteCount")
    private Long favoriteCount;

    @JsonProperty("commentCount")
    private Long commentCount;
}
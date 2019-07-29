package com.tubecentric.old.external.api.youtube.model.video;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "viewCount",
        "likeCount",
        "dislikeCount",
        "favoriteCount",
        "commentCount"
})
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

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("viewCount")
    public Long getViewCount() {
        return viewCount;
    }

    @JsonProperty("viewCount")
    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    @JsonProperty("likeCount")
    public Long getLikeCount() {
        return likeCount;
    }

    @JsonProperty("likeCount")
    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    @JsonProperty("dislikeCount")
    public Long getDislikeCount() {
        return dislikeCount;
    }

    @JsonProperty("dislikeCount")
    public void setDislikeCount(Long dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    @JsonProperty("favoriteCount")
    public Long getFavoriteCount() {
        return favoriteCount;
    }

    @JsonProperty("favoriteCount")
    public void setFavoriteCount(Long favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    @JsonProperty("commentCount")
    public Long getCommentCount() {
        return commentCount;
    }

    @JsonProperty("commentCount")
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
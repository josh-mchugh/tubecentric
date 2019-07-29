package com.tubecentric.old.external.api.youtube.model.video;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tubecentric.old.external.api.youtube.model.thumbnail.Thumbnails;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "publishedAt",
        "channelId",
        "title",
        "description",
        "thumbnails",
        "channelTitle",
        "tags",
        "categoryId",
        "liveBroadcastContent",
        "defaultLanguage",
        "localized",
        "defaultAudioLanguage"
})
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
    private List<String> tags = null;

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

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("publishedAt")
    public String getPublishedAt() {
        return publishedAt;
    }

    @JsonProperty("publishedAt")
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @JsonProperty("channelId")
    public String getChannelId() {
        return channelId;
    }

    @JsonProperty("channelId")
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("thumbnails")
    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    @JsonProperty("thumbnails")
    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    @JsonProperty("channelTitle")
    public String getChannelTitle() {
        return channelTitle;
    }

    @JsonProperty("channelTitle")
    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @JsonProperty("categoryId")
    public String getCategoryId() {
        return categoryId;
    }

    @JsonProperty("categoryId")
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @JsonProperty("liveBroadcastContent")
    public String getLiveBroadcastContent() {
        return liveBroadcastContent;
    }

    @JsonProperty("liveBroadcastContent")
    public void setLiveBroadcastContent(String liveBroadcastContent) {
        this.liveBroadcastContent = liveBroadcastContent;
    }

    @JsonProperty("defaultLanguage")
    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    @JsonProperty("defaultLanguage")
    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    @JsonProperty("localized")
    public Localized getLocalized() {
        return localized;
    }

    @JsonProperty("localized")
    public void setLocalized(Localized localized) {
        this.localized = localized;
    }

    @JsonProperty("defaultAudioLanguage")
    public String getDefaultAudioLanguage() {
        return defaultAudioLanguage;
    }

    @JsonProperty("defaultAudioLanguage")
    public void setDefaultAudioLanguage(String defaultAudioLanguage) {
        this.defaultAudioLanguage = defaultAudioLanguage;
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
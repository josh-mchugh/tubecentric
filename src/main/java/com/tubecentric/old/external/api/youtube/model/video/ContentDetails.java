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
        "duration",
        "dimension",
        "definition",
        "caption",
        "licensedContent",
        "projection"
})
public class ContentDetails {

    @JsonProperty("duration")
    private String duration;

    @JsonProperty("dimension")
    private String dimension;

    @JsonProperty("definition")
    private String definition;

    @JsonProperty("caption")
    private String caption;

    @JsonProperty("licensedContent")
    private boolean licensedContent;

    @JsonProperty("projection")
    private String projection;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("duration")
    public String getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(String duration) {
        this.duration = duration;
    }

    @JsonProperty("dimension")
    public String getDimension() {
        return dimension;
    }

    @JsonProperty("dimension")
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    @JsonProperty("definition")
    public String getDefinition() {
        return definition;
    }

    @JsonProperty("definition")
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @JsonProperty("caption")
    public String getCaption() {
        return caption;
    }

    @JsonProperty("caption")
    public void setCaption(String caption) {
        this.caption = caption;
    }

    @JsonProperty("licensedContent")
    public boolean isLicensedContent() {
        return licensedContent;
    }

    @JsonProperty("licensedContent")
    public void setLicensedContent(boolean licensedContent) {
        this.licensedContent = licensedContent;
    }

    @JsonProperty("projection")
    public String getProjection() {
        return projection;
    }

    @JsonProperty("projection")
    public void setProjection(String projection) {
        this.projection = projection;
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
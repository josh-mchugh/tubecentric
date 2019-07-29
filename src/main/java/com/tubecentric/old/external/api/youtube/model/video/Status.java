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
        "uploadStatus",
        "privacyStatus",
        "license",
        "embeddable",
        "publicStatsViewable"
})
public class Status {

    @JsonProperty("uploadStatus")
    private String uploadStatus;

    @JsonProperty("privacyStatus")
    private String privacyStatus;

    @JsonProperty("license")
    private String license;

    @JsonProperty("embeddable")
    private boolean embeddable;

    @JsonProperty("publicStatsViewable")
    private boolean publicStatsViewable;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("uploadStatus")
    public String getUploadStatus() {
        return uploadStatus;
    }

    @JsonProperty("uploadStatus")
    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    @JsonProperty("privacyStatus")
    public String getPrivacyStatus() {
        return privacyStatus;
    }

    @JsonProperty("privacyStatus")
    public void setPrivacyStatus(String privacyStatus) {
        this.privacyStatus = privacyStatus;
    }

    @JsonProperty("license")
    public String getLicense() {
        return license;
    }

    @JsonProperty("license")
    public void setLicense(String license) {
        this.license = license;
    }

    @JsonProperty("embeddable")
    public boolean isEmbeddable() {
        return embeddable;
    }

    @JsonProperty("embeddable")
    public void setEmbeddable(boolean embeddable) {
        this.embeddable = embeddable;
    }

    @JsonProperty("publicStatsViewable")
    public boolean isPublicStatsViewable() {
        return publicStatsViewable;
    }

    @JsonProperty("publicStatsViewable")
    public void setPublicStatsViewable(boolean publicStatsViewable) {
        this.publicStatsViewable = publicStatsViewable;
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
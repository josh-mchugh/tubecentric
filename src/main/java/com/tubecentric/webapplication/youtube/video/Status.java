package com.tubecentric.webapplication.youtube.video;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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
}
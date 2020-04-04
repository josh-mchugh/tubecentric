package com.tubecentric.webapplication.youtube.video;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Status {

    @JsonProperty("uploadStatus")
    private String uploadStatus;

    @JsonProperty("failureReason")
    private String failureReason;

    @JsonProperty("rejectionReason")
    private String rejectionReason;

    @JsonProperty("privacyStatus")
    private String privacyStatus;

    @JsonProperty("publishAt")
    private LocalDateTime publishAt;

    @JsonProperty("license")
    private String license;

    @JsonProperty("embeddable")
    private Boolean embeddable;

    @JsonProperty("publicStatsViewable")
    private Boolean publicStatsViewable;

    @JsonProperty("madeForKids")
    private Boolean madeForKids;

    @JsonProperty("selfDeclaredMadeForKids")
    private Boolean selfDeclaredMadeForKids;
}
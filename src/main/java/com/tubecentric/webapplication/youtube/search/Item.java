package com.tubecentric.webapplication.youtube.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Item {

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("etag")
    private String etag;

    @JsonProperty("id")
    private Id id;

    @JsonProperty("snippet")
    private Snippet snippet;
}

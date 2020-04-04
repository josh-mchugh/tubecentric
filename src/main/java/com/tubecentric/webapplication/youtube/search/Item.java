package com.tubecentric.webapplication.youtube.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Item {

    @JsonProperty("id")
    private Id id;

    @JsonProperty("snippet")
    private Snippet snippet;
}

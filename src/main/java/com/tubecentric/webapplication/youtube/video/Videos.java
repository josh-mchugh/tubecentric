package com.tubecentric.webapplication.youtube.video;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tubecentric.webapplication.youtube.PageInfo;
import lombok.Data;

import java.util.List;

@Data
public class Videos {

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("etag")
    private String etag;

    @JsonProperty("pageInfo")
    private PageInfo pageInfo;

    @JsonProperty("items")
    private List<Item> items;
}
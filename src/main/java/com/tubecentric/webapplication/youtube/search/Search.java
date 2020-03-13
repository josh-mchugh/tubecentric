package com.tubecentric.webapplication.youtube.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tubecentric.webapplication.youtube.PageInfo;
import lombok.Data;

import java.util.List;

@Data
public class Search {

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("etag")
    private String etag;

    @JsonProperty("nextPageToken")
    private String nextPageToken;

    @JsonProperty("prevPageToken")
    private String prevPageToken;

    @JsonProperty("regionCode")
    private String regionCode;

    @JsonProperty("pageInfo")
    private PageInfo pageInfo;

    @JsonProperty("items")
    private List<Item> items;
}
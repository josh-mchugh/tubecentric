package com.tubecentric.webapplication.youtube;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PageInfo {

    @JsonProperty("totalResults")
    private Long totalResults;

    @JsonProperty("resultsPerPage")
    private Long resultsPerPage;
}
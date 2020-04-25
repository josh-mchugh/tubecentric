package com.tubecentric.webapplication.web.search.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RankedTag {

    private String tag;
    private Integer ratio;
}

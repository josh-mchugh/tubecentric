package com.tubecentric.webapplication.youtube.video;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TopicDetails {

    @JsonProperty("relevantTopicIds")
    private List<String> relevantTopicIds;

    @JsonProperty("topicCategories")
    private List<String> topicCategories;
}
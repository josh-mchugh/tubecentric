package com.tubecentric.old.external.api.youtube.model.video;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "relevantTopicIds",
        "topicCategories"
})
public class TopicDetails {

    @JsonProperty("relevantTopicIds")
    private List<String> relevantTopicIds = null;

    @JsonProperty("topicCategories")
    private List<String> topicCategories = null;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("relevantTopicIds")
    public List<String> getRelevantTopicIds() {
        return relevantTopicIds;
    }

    @JsonProperty("relevantTopicIds")
    public void setRelevantTopicIds(List<String> relevantTopicIds) {
        this.relevantTopicIds = relevantTopicIds;
    }

    @JsonProperty("topicCategories")
    public List<String> getTopicCategories() {
        return topicCategories;
    }

    @JsonProperty("topicCategories")
    public void setTopicCategories(List<String> topicCategories) {
        this.topicCategories = topicCategories;
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
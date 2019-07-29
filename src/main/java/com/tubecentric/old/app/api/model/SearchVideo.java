package com.tubecentric.old.app.api.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class SearchVideo {

    private final String id;
    private final LocalDateTime publishedAt;
    private final String thumbnailUrl;
    private final String channelTitle;
    private final List<String> tags;
    private final String title;
    private final String description;
    private final Long viewCount;
}

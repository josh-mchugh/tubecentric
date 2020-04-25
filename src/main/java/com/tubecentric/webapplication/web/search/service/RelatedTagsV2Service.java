package com.tubecentric.webapplication.web.search.service;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.tubecentric.webapplication.framework.config.AppConfig;
import com.tubecentric.webapplication.metric.IMetricRelatedTagsService;
import com.tubecentric.webapplication.web.search.model.RankedTag;
import com.tubecentric.webapplication.web.search.model.RankedTags;
import com.tubecentric.webapplication.youtube.client.YouTubeClient;
import com.tubecentric.webapplication.youtube.client.model.SearchParams;
import com.tubecentric.webapplication.youtube.client.model.VideoParams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Singular;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RelatedTagsV2Service implements IRelatedTagsV2Service {

    private final AppConfig appConfig;
    private final YouTubeClient youTubeClient;
    private final IMetricRelatedTagsService metricRelatedTagsService;

    @Override
    public RankedTags getRankedTags(String query) {

        metricRelatedTagsService.persistRelatedTagsQuery(query);

        SearchParams searchParams = SearchParams.builder()
                .q(query)
                .maxResults(25)
                .key(appConfig.getYouTube().getKey())
                .build();

        String videoIds = youTubeClient.getSearchResults(searchParams).getItems().stream()
                .map(results -> results.getId().getVideoId())
                .collect(Collectors.joining(","));

        VideoParams videoParams = VideoParams.builder()
                .id(videoIds)
                .key(appConfig.getYouTube().getKey())
                .build();

        Set<String> tags = youTubeClient.getVideos(videoParams).getItems().stream()
                .flatMap(video -> video.getSnippet().getTags().stream())
                .collect(Collectors.toSet());

        return RankedTags.builder()
                .query(query)
                .words(Splitter.on(" ").splitToList(query.trim()))
                .rankedTags(getRankedTags(query, tags))
                .build();
    }

    public List<RankedTag> getRankedTags(String query, Set<String> tags) {

        return buildRankedTags(query, tags).stream()
                .sorted((o1, o2) -> o2.getRatio().compareTo(o1.getRatio()))
                .limit(20)
                .collect(Collectors.toList());
    }

    private Set<RankedTag> buildRankedTags(String query, Set<String> tags) {

        Set<RankedTag> rankedTags = new HashSet<>();

        for (String tag : tags) {

            int ratio = FuzzySearch.ratio(query, tag);

            if (ratio > 45) {

                RankedTag rankedTag = RankedTag.builder()
                        .tag(tag)
                        .ratio(ratio)
                        .build();

                rankedTags.add(rankedTag);
            }
        }

        return rankedTags;
    }
}
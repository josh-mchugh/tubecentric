package com.tubecentric.webapplication.web.keyword.service;

import com.tubecentric.webapplication.framework.config.AppConfig;
import com.tubecentric.webapplication.keyword.IKeywordSearchService;
import com.tubecentric.webapplication.youtube.client.YouTubeClient;
import com.tubecentric.webapplication.youtube.client.model.SearchParams;
import com.tubecentric.webapplication.youtube.client.model.VideoParams;
import lombok.RequiredArgsConstructor;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TagSearchService implements ITagSearchService {

    private final AppConfig appConfig;
    private final YouTubeClient youTubeClient;
    private final IKeywordSearchService keywordSearchService;

    @Override
    public List<ExtractedResult> getSearchTags(String query) {

        keywordSearchService.addKeywordSearchTerm(query);

        SearchParams searchParams = SearchParams.builder()
                .q(query)
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

        return FuzzySearch.extractTop(query, tags, 20, 35);
    }
}

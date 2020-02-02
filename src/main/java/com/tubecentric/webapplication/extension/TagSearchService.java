package com.tubecentric.webapplication.extension;

import com.tubecentric.webapplication.extension.youtube.IYouTubeClient;
import com.tubecentric.webapplication.extension.youtube.model.SearchParams;
import com.tubecentric.webapplication.extension.youtube.model.VideoParams;
import lombok.RequiredArgsConstructor;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TagSearchService implements ITagSearchService {

    private final IYouTubeClient youTubeClient;

    @Value("${app.youtube.api.key}")
    private String apiKey;

    @Override
    public List<ExtractedResult> getSearchTags(String query) {

        SearchParams searchParams = SearchParams.builder()
                .q(query)
                .key(apiKey)
                .build();

        String videoIds = youTubeClient.getSearchResults(searchParams).getItems().stream()
                .map(results -> results.getId().getVideoId())
                .collect(Collectors.joining(","));

        VideoParams videoParams = VideoParams.builder()
                .id(videoIds)
                .key(apiKey)
                .build();

        Set<String> tags = youTubeClient.getVideos(videoParams).getItems().stream()
                .flatMap(video -> video.getSnippet().getTags().stream())
                .collect(Collectors.toSet());

        return FuzzySearch.extractTop(query, tags, 20, 35);
    }
}

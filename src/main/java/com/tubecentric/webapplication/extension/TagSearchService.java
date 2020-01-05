package com.tubecentric.webapplication.extension;

import com.tubecentric.webapplication.extension.youtube.IYouTubeClient;
import com.tubecentric.webapplication.extension.youtube.model.SearchParams;
import com.tubecentric.webapplication.extension.youtube.model.VideoParams;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TagSearchService implements ITagSearchService {

    @Autowired
    private IYouTubeClient youTubeClient;

    @Value("${app.youtube.api.key}")
    private String apiKey;

    @Override
    public List<ExtractedResult> getSearchTags() {

        SearchParams searchParams = SearchParams.builder()
                .q("Hot dogs horseshoes hand grenades")
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

        return FuzzySearch.extractTop("Hot dogs horseshoes hand grenades", tags, 25, 35);
    }
}

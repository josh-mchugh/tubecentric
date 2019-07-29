package com.tubecentric.old.app.api.videos;

import com.tubecentric.old.app.api.model.SearchVideo;
import com.tubecentric.old.app.api.model.VideosSearchResponse;
import com.tubecentric.old.external.api.youtube.model.search.Search;
import com.tubecentric.old.external.api.youtube.model.video.Item;
import com.tubecentric.old.external.api.youtube.model.video.Videos;
import com.tubecentric.old.external.api.youtube.retrofit.IYouTubeRetrofit;
import io.reactivex.Single;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class VideosController {

    @Autowired
    private IVideoControllerService videoControllerService;

    @Autowired
    private IYouTubeRetrofit retrofit;

    @GetMapping("/video-meta-builder")
    public RedirectView getDefault() {

        return new RedirectView("/video-meta-builder/index.html");
    }

    @RequestMapping(value = "/video-meta-builder/api/search", method = RequestMethod.GET)
    public Single<VideosSearchResponse> getSearch(@RequestParam(value = "query") String query, @RequestParam(value = "language", defaultValue = "en") String language, @RequestParam(value="pageToken", defaultValue = "") String pageToken) {

        return retrofit.retrofitService()
                .search(videoControllerService.getSearchParams(query, pageToken))
                .flatMap(search -> retrofit.retrofitService()
                        .videos(videoControllerService.getVideoParams(getVideoIds(search), language))
                            .flatMap(videos ->  Single.just(buildSearchResponse(query, search, videos)))
                );
    }

    private List<String> getVideoIds(Search results) {

        return results.getItems().stream()
                .map(item -> item.getId().getVideoId())
                .collect(Collectors.toList());
    }

    private VideosSearchResponse buildSearchResponse(String query, Search search, Videos videos) {

        VideosSearchResponse.VideosSearchResponseBuilder response = VideosSearchResponse.builder()
                .query(query)
                .hasNextPage(StringUtils.isNotBlank(search.getNextPageToken()))
                .nextPageToken(search.getNextPageToken())
                .hasPrevPage(StringUtils.isNotBlank(search.getPrevPageToken()))
                .prevPageToken(search.getPrevPageToken())
                .regionCode(search.getRegionCode())
                .totalResults(search.getPageInfo().getTotalResults())
                .resultsPerPage(search.getPageInfo().getResultsPerPage());

        videos.getItems().forEach(item -> response.video(buildSearchVideo(item)));

        return response.build();
    }

    private SearchVideo buildSearchVideo(Item item) {

        return SearchVideo.builder()
                .id(item.getId())
                .publishedAt(LocalDateTime.parse(item.getSnippet().getPublishedAt(), DateTimeFormatter.ISO_DATE_TIME))
                .channelTitle(item.getSnippet().getChannelTitle())
                .thumbnailUrl(item.getSnippet().getThumbnails().getMedium().getUrl())
                .tags(item.getSnippet().getTags())
                .title(item.getSnippet().getLocalized().getTitle())
                .description(item.getSnippet().getLocalized().getDescription())
                .viewCount(item.getStatistics().getViewCount())
                .build();
    }
}

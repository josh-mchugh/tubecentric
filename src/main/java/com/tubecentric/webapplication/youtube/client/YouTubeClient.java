package com.tubecentric.webapplication.youtube.client;

import com.tubecentric.webapplication.youtube.client.model.SearchParams;
import com.tubecentric.webapplication.youtube.client.model.VideoParams;
import com.tubecentric.webapplication.youtube.search.Search;
import com.tubecentric.webapplication.youtube.video.Videos;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "youtubeClient",
        url = "${app.youtube.base-url}"
)
public interface YouTubeClient {

    @GetMapping("search")
    Search getSearchResults (@SpringQueryMap SearchParams params);

    @GetMapping("videos")
    Videos getVideos (@SpringQueryMap VideoParams params);
}

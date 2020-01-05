package com.tubecentric.webapplication.extension.youtube;

import com.tubecentric.old.external.api.youtube.model.video.Videos;
import com.tubecentric.webapplication.extension.youtube.model.SearchParams;
import com.tubecentric.old.external.api.youtube.model.search.Search;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="youTubeClient", url="${app.youtube.base.url}")
public interface IYouTubeClient {

    @GetMapping("search")
    Search getSearchResults(@SpringQueryMap SearchParams params);

    @GetMapping("videos")
    Videos getVideos(@SpringQueryMap Object params);
}

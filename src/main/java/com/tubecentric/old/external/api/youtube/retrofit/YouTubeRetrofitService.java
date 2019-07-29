package com.tubecentric.old.external.api.youtube.retrofit;

import com.tubecentric.old.external.api.youtube.model.search.Search;
import com.tubecentric.old.external.api.youtube.model.video.Videos;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface YouTubeRetrofitService {

    @GET("videos")
    Single<Videos> videos(@QueryMap Map<String, String> query);

    @GET("search")
    Single<Search> search(@QueryMap Map<String, String> query);
}

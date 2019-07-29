package com.tubecentric.old.external.api.youtube.retrofit;

import com.tubecentric.old.framework.network.IOkHttpService;
import com.tubecentric.old.framework.network.IRetrofitBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Component
public class YouTubeRetrofit implements IYouTubeRetrofit {

    @Autowired
    private IOkHttpService okHttpService;

    @Autowired
    private IRetrofitBuilder retrofitBuilder;

    @Value("${app.youtube.base.url}")
    private String baseURL;

    private Retrofit retrofit;

    @Override
    public Retrofit retrofit() {

        if(retrofit != null) {

            return retrofit;
        }

        retrofit = retrofitBuilder.retrofitBuilder()
                .baseUrl(baseURL)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpService.okHttpClient())
                .build();

        return retrofit;
    }

    @Override
    public YouTubeRetrofitService retrofitService() {

        return retrofit().create(YouTubeRetrofitService.class);
    }
}

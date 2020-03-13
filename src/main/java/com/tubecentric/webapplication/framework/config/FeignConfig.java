package com.tubecentric.webapplication.framework.config;

import feign.okhttp.OkHttpClient;
import okhttp3.ConnectionPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeignConfig {

    private okhttp3.OkHttpClient okHttp3Client() {
        return new okhttp3.OkHttpClient().newBuilder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool())
                .build();
    }

    @Bean
    public OkHttpClient okHttpClient() {

        return new OkHttpClient(okHttp3Client());
    }
}

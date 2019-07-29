package com.tubecentric.old.framework.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OkHTTPService implements IOkHttpService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OkHTTPService.class);

    @Override
    public OkHttpClient okHttpClient() {

        return new OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor())
                .build();
    }

    private HttpLoggingInterceptor loggingInterceptor() {

        return new HttpLoggingInterceptor(message -> LOGGER.info(message))
                .setLevel(HttpLoggingInterceptor.Level.BASIC);
    }
}

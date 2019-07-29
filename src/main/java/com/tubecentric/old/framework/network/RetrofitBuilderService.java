package com.tubecentric.old.framework.network;

import org.springframework.stereotype.Component;
import retrofit2.Retrofit;

@Component
public class RetrofitBuilderService implements IRetrofitBuilder {

    @Override
    public Retrofit.Builder retrofitBuilder() {

        return new Retrofit.Builder();
    }
}

package com.example.vahe.newsfeed.di;

import com.example.vahe.newsfeed.http.RequestHelper;
import com.example.vahe.newsfeed.http.RequestHelperImpl;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RequestModule {

    private RequestHelper requestHelper;

    public RequestModule() {
        requestHelper = new RequestHelperImpl();
    }

    @Provides
    @Singleton
    @Named("requestHelper")
    public RequestHelper requestHelperProvider() {
        return requestHelper;
    }
}

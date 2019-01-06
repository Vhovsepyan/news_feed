package com.example.vahe.newsfeed;

import android.app.Application;

import com.example.vahe.newsfeed.di.AppComponent;
import com.example.vahe.newsfeed.di.DaggerAppComponent;
import com.example.vahe.newsfeed.di.DatabaseModule;
import com.example.vahe.newsfeed.di.RepositoryModule;
import com.example.vahe.newsfeed.di.RequestModule;
import com.example.vahe.newsfeed.http.RestApi;
import com.example.vahe.newsfeed.http.RestApiFactory;
import com.example.vahe.newsfeed.utils.SharedPrefs;

public class NewsFeedApp extends Application {

    private AppComponent appComponent;

    private RestApi restApi;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .repositoryModule(new RepositoryModule())
                .databaseModule(new DatabaseModule(this))
                .requestModule(new RequestModule())
                .build();
        SharedPrefs.initialize(this);

    }

    public AppComponent appComponent() {
        return appComponent;
    }

    public RestApi getRestApi() {
        if(restApi == null) {
            restApi = RestApiFactory.create();
        }
        return restApi;
    }
}

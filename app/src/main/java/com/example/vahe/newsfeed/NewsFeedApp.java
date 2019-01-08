package com.example.vahe.newsfeed;

import android.app.Application;

import com.example.vahe.newsfeed.di.AppComponent;
import com.example.vahe.newsfeed.di.DaggerAppComponent;
import com.example.vahe.newsfeed.di.DatabaseModule;
import com.example.vahe.newsfeed.di.RepositoryModule;
import com.example.vahe.newsfeed.utils.SharedPrefs;

public class NewsFeedApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .repositoryModule(new RepositoryModule())
                .databaseModule(new DatabaseModule(this))
                .build();
        SharedPrefs.initialize(this);

    }

    public AppComponent appComponent() {
        return appComponent;
    }

}

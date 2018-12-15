package com.example.vahe.newsfeed1;

import android.app.Application;

import com.example.vahe.newsfeed1.di.AppComponent;
import com.example.vahe.newsfeed1.di.DaggerAppComponent;
import com.example.vahe.newsfeed1.di.DatabaseModule;
import com.example.vahe.newsfeed1.utils.SharedPrefs;

public class NewsFeedApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .databaseModule(new DatabaseModule(this))
                .build();
        SharedPrefs.initialize(this);

    }

    public AppComponent appComponent() {
        return appComponent;
    }
}

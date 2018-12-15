package com.example.vahe.newsfeed.di;

import android.arch.persistence.room.Room;

import com.example.vahe.newsfeed.NewsFeedApp;
import com.example.vahe.newsfeed.dao.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    private static final String DB_NAME = "GlobeDatabase.db";

    private AppDatabase demoDatabase;

    public DatabaseModule(NewsFeedApp app) {
        this.demoDatabase = Room.databaseBuilder(app, AppDatabase.class, DB_NAME).build();
    }

    @Provides
    @Singleton
    AppDatabase appDatabaseProvider() {
        return demoDatabase;
    }


}

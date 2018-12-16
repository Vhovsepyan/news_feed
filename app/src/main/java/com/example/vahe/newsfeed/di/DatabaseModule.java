package com.example.vahe.newsfeed.di;

import android.arch.persistence.room.Room;

import com.example.vahe.newsfeed.NewsFeedApp;
import com.example.vahe.newsfeed.dao.AppDatabase;
import com.example.vahe.newsfeed.dao.ArticleDao;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    private static final String DB_NAME = "NewsFeed.db";

    private AppDatabase demoDatabase;

    public DatabaseModule(NewsFeedApp app) {
        this.demoDatabase = Room.databaseBuilder(app, AppDatabase.class, DB_NAME).build();
    }

    @Provides
    @Singleton
    @Named("database")
    AppDatabase appDatabaseProvider() {
        return demoDatabase;
    }

    @Singleton
    @Provides
    public ArticleDao provideArticleDao(AppDatabase myDatabase) {
        return myDatabase.getArticleDao();
    }

}

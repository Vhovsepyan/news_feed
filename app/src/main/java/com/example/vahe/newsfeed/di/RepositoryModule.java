package com.example.vahe.newsfeed.di;

import com.example.vahe.newsfeed.dao.AppDatabase;
import com.example.vahe.newsfeed.repository.NewsRepository;
import com.example.vahe.newsfeed.repository.NewsRepositoryImpl;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = DatabaseModule.class
)
public class RepositoryModule {

    public RepositoryModule() {
    }

    @Provides
    @Singleton
    public NewsRepository provideNewsRepository(@Named("database")AppDatabase database){
        return new NewsRepositoryImpl(database.getArticleDao());
    }
}

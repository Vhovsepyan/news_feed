package com.example.vahe.newsfeed.di;

import com.example.vahe.newsfeed.dao.AppDatabase;
import com.example.vahe.newsfeed.http.RequestHelper;
import com.example.vahe.newsfeed.repository.ArticleRepository;
import com.example.vahe.newsfeed.repository.ArticleRepositoryImpl;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {
                DatabaseModule.class,
                RequestModule.class
        }
        )
public class RepositoryModule {

    public RepositoryModule() {
    }

    @Provides
    @Singleton
    public ArticleRepository provideNewsRepository(@Named("database")AppDatabase database, @Named("requestHelper")RequestHelper requestHelper){
        return new ArticleRepositoryImpl(database.getArticleDao(), requestHelper);
    }
}

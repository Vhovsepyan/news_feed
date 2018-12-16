package com.example.vahe.newsfeed.di;

import com.example.vahe.newsfeed.http.RequestHelper;
import com.example.vahe.newsfeed.screens.MainActivity;
import com.example.vahe.newsfeed.screens.home.PageInfoVM;
import com.example.vahe.newsfeed.screens.info.ArticleVM;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DatabaseModule.class, RepositoryModule.class})
public interface AppComponent {
    void inject(ArticleVM articleVM);

    void inject(PageInfoVM pageInfoVM);

    void inject(RequestHelper requestHelper);

}

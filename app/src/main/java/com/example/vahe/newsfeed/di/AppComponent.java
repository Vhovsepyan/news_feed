package com.example.vahe.newsfeed.di;

import com.example.vahe.newsfeed.screens.MainActivity;
import com.example.vahe.newsfeed.screens.home.PageInfoVM;
import com.example.vahe.newsfeed.screens.info.ArticleVM;
import com.example.vahe.newsfeed.service.MyJobService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DatabaseModule.class, RepositoryModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(ArticleVM articleVM);

    void inject(PageInfoVM pageInfoVM);

    void inject(MyJobService jobService);
}

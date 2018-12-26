package com.example.vahe.newsfeed.di;

import com.example.vahe.newsfeed.view.MainActivity;
import com.example.vahe.newsfeed.view.home.ArticleListViewModel;
import com.example.vahe.newsfeed.view.info.ArticleVM;
import com.example.vahe.newsfeed.service.MyJobService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DatabaseModule.class, RepositoryModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(ArticleVM articleVM);

    void inject(ArticleListViewModel articleListViewModel);

    void inject(MyJobService jobService);
}

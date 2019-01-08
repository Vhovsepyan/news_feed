package com.example.vahe.newsfeed.di;

import com.example.vahe.newsfeed.view.MainActivity;
import com.example.vahe.newsfeed.view.home.ArticleListViewModel;
import com.example.vahe.newsfeed.view.info.ArticleVM;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DatabaseModule.class, RepositoryModule.class})
public interface AppComponent {

    void inject(ArticleVM articleVM);

    void inject(ArticleListViewModel articleListViewModel);

}

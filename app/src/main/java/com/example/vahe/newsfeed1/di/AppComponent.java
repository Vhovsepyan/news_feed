package com.example.vahe.newsfeed1.di;

import com.example.vahe.newsfeed1.http.RequestHelper;
import com.example.vahe.newsfeed1.screens.MainActivity;
import com.example.vahe.newsfeed1.screens.home.PageInfoVM;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DatabaseModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(PageInfoVM pageInfoVM);

    void inject(RequestHelper requestHelper);

}

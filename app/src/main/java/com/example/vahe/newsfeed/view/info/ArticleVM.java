package com.example.vahe.newsfeed.view.info;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.vahe.newsfeed.NewsFeedApp;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.repository.ArticleRepository;
import com.example.vahe.newsfeed.view.BaseVM;

import javax.inject.Inject;

public class ArticleVM extends BaseVM {

    @Inject
    public ArticleRepository articleRepository;
    public Article article;
    private LiveData<Article> articleLiveData ;

    public ArticleVM(Application app) {
        super(app);
        ((NewsFeedApp)app).appComponent().inject(this);
    }

    public void init(String id, String showFields, String apiKey){
        articleLiveData = articleRepository.getPageById(id, showFields, apiKey);
    }

    public LiveData<Article> getArticleLiveData() {
        return articleLiveData;
    }
}

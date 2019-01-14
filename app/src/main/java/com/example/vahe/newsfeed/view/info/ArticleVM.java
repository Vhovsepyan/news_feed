package com.example.vahe.newsfeed.view.info;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.example.vahe.newsfeed.NewsFeedApp;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.repository.ArticleRepository;
import com.example.vahe.newsfeed.view.BaseVM;

import javax.inject.Inject;

public class ArticleVM extends BaseVM {

    @Inject
    public ArticleRepository articleRepository;
    private LiveData<Article> articleLiveData;

    public ObservableBoolean isProgessBarVisible = new ObservableBoolean(true);
    public ObservableField<Article> article = new ObservableField<>();

    public ArticleVM(Application app) {
        super(app);
        ((NewsFeedApp) app).appComponent().inject(this);
    }

    public void init(String apiUrl, String showFields) {
        articleLiveData = articleRepository.getPageByApiUrl(apiUrl, showFields);
    }

    public LiveData<Article> getArticleLiveData() {
        return articleLiveData;
    }

    public void setArticle(Article article) {
        this.article.set(article);
        isProgessBarVisible.set(false);
    }
}

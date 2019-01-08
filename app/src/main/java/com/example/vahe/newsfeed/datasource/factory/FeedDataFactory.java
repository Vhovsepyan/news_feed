package com.example.vahe.newsfeed.datasource.factory;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.example.vahe.newsfeed.datasource.FeedDataSource;
import com.example.vahe.newsfeed.repository.ArticleRepository;

public class FeedDataFactory extends DataSource.Factory {

    private MutableLiveData<FeedDataSource> mutableLiveData;
    private FeedDataSource feedDataSource;
    private ArticleRepository articleRepository;

    public FeedDataFactory(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
        this.mutableLiveData = new MutableLiveData<FeedDataSource>();
    }

    @Override
    public DataSource create() {
        feedDataSource = new FeedDataSource(articleRepository);
        mutableLiveData.postValue(feedDataSource);
        return feedDataSource;
    }


    public MutableLiveData<FeedDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}

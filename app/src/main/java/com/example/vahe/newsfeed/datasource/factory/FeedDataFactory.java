package com.example.vahe.newsfeed.datasource.factory;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.example.vahe.newsfeed.NewsFeedApp;
import com.example.vahe.newsfeed.datasource.FeedDataSource;

public class FeedDataFactory extends DataSource.Factory {

    private MutableLiveData<FeedDataSource> mutableLiveData;
    private FeedDataSource feedDataSource;
    private NewsFeedApp app;

    public FeedDataFactory(NewsFeedApp app) {
        this.app = app;
        this.mutableLiveData = new MutableLiveData<FeedDataSource>();
    }

    @Override
    public DataSource create() {
        feedDataSource = new FeedDataSource(app);
        mutableLiveData.postValue(feedDataSource);
        return feedDataSource;
    }


    public MutableLiveData<FeedDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}

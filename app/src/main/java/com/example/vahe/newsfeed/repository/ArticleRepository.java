package com.example.vahe.newsfeed.repository;

import android.arch.lifecycle.LiveData;

import com.example.vahe.newsfeed.listener.OnListReadyListener;
import com.example.vahe.newsfeed.listener.OnObjectReadyListener;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.PageInfo;

public interface ArticleRepository extends BaseRepository<Article> {

    LiveData<PageInfo> getPageInfoFromApi(String url);

}

package com.example.vahe.newsfeed.repository;

import com.example.vahe.newsfeed.listener.OnListReadyListener;
import com.example.vahe.newsfeed.model.Article;

public interface NewsRepository extends BaseRepository<Article> {
    void getPageInfoFromApi(String url, OnListReadyListener onListReadyListener);

    void getArticlesFromDB(OnListReadyListener onListReadyListener);

}

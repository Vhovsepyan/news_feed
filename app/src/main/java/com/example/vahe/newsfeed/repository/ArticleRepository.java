package com.example.vahe.newsfeed.repository;

import com.example.vahe.newsfeed.listener.OnDataReadyListener;
import com.example.vahe.newsfeed.listener.OnListReadyListener;
import com.example.vahe.newsfeed.listener.OnObjectReadyListener;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.PageInfo;

public interface ArticleRepository extends BaseRepository<Article> {

    void getStringDataFromApi(String url, OnDataReadyListener<String> onListReadyListener);

    void getPageInfoFromApi(String url, OnObjectReadyListener<PageInfo> onListReadyListener);

    void getArticlesFromApi(String url, OnListReadyListener<Article> onListReadyListener);

    void getArticlesFromDB(OnListReadyListener onListReadyListener);

}

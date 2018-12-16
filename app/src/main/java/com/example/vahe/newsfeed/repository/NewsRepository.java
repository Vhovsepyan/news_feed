package com.example.vahe.newsfeed.repository;

import com.example.vahe.newsfeed.listener.OnListReadyListener;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.PageInfo;

import java.util.List;

public interface NewsRepository extends BaseRepository<Article>  {
    PageInfo getPageInfoFromApi();

    void getArticlesFromDB(OnListReadyListener onListReadyListener);

}

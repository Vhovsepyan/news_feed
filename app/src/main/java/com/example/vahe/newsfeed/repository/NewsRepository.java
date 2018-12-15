package com.example.vahe.newsfeed.repository;

import com.example.vahe.newsfeed.model.PageInfo;

public interface NewsRepository {
    PageInfo getPageInfoFromApi();
}

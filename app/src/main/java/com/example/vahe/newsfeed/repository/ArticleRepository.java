package com.example.vahe.newsfeed.repository;

import android.arch.lifecycle.LiveData;

import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.request.PageInfoResponseModel;
import com.example.vahe.newsfeed.model.request.BaseResponseModel;

import retrofit2.Call;

public interface ArticleRepository extends BaseRepository<Article> {

    Call<BaseResponseModel<PageInfoResponseModel>> getNewsPageInfo(long page , long pageSize);

    LiveData<Article> getPageById(String id, String showFields, String apiKey);

}

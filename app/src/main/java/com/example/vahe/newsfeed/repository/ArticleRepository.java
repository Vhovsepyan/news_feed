package com.example.vahe.newsfeed.repository;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.NetworkState;
import com.example.vahe.newsfeed.model.request.BaseResponseModel;
import com.example.vahe.newsfeed.model.request.PageInfoResponseModel;

import retrofit2.Call;

public interface ArticleRepository extends BaseRepository<Article> {

    Call<BaseResponseModel<PageInfoResponseModel>> getNewsPageInfo(long page, long pageSize);

    LiveData<Article> getPageByApiUrl(String apiUrl, String showFields);

    LiveData<NetworkState> getNetworkState();

    LiveData<PagedList<Article>> getArticleLiveData();

}

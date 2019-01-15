package com.example.vahe.newsfeed.http;

import com.example.vahe.newsfeed.model.request.ArticleResponseModel;
import com.example.vahe.newsfeed.model.request.BaseResponseModel;
import com.example.vahe.newsfeed.model.request.PageInfoResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RestApi {
    @GET("/search")
    Call<BaseResponseModel<PageInfoResponseModel>> getNewsPageInfo(@Query("page") long page,
                                                                   @Query("pageSize") long pageSize,
                                                                   @Query("show-fields") String showFields);

    @GET
    Call<BaseResponseModel<ArticleResponseModel>> getPageByApiUrl(
            @Url String apiUrl,
            @Query("show-fields") String fields
    );
}

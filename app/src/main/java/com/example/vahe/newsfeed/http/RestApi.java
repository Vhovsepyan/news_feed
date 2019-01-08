package com.example.vahe.newsfeed.http;

import com.example.vahe.newsfeed.model.request.ArticleResponseModel;
import com.example.vahe.newsfeed.model.request.BaseResponseModel;
import com.example.vahe.newsfeed.model.request.PageInfoResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {
    @GET("search?show-fields=thumbnail,trailText,headline")
    Call<BaseResponseModel<PageInfoResponseModel>> getNewsPageInfo(@Query("page") long page,
                                                                   @Query("pageSize") long pageSize);

    @GET("{id}")
    Call<BaseResponseModel<ArticleResponseModel>> getPageById(
            @Path("id") String id,
            @Query("show-fields") String showFields,
            @Query("api-key") String apiKey
    );
}

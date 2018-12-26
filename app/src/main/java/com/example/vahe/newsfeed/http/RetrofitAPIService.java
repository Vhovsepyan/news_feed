package com.example.vahe.newsfeed.http;

import com.example.vahe.newsfeed.model.request.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPIService {
    @GET("search?api-key=5c732610-0d3a-43fa-828e-12ae6b8ebd85&show-fields=thumbnail,trailText,headline")
    Call<ResponseModel> getPageInfo();
}

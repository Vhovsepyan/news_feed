package com.example.vahe.newsfeed.http;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.vahe.newsfeed.model.BaseObject;
import com.example.vahe.newsfeed.model.PageInfo;
import com.example.vahe.newsfeed.model.request.ResponseModel;
import com.example.vahe.newsfeed.model.request.PageInfoResponseModel;
import com.example.vahe.newsfeed.utils.AppLog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestHelperImpl<T extends BaseObject> implements RequestHelper {
    private RestApi apiService;
    private Retrofit retrofit;
    private static final String BASE_URL = "https://content.guardianapis.com/";

    public RequestHelperImpl() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(RestApi.class);
    }

    /*@Override
    public LiveData<PageInfo> getPageInfo() {
        final MutableLiveData<PageInfo> data = new MutableLiveData<>();
        apiService.getPageInfo().enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
                AppLog.i(" onResponse = " + response.body());
                if (response.isSuccessful()){
                    ResponseModel responseModel = response.body();
                    if (responseModel != null){
                        PageInfoResponseModel requestModel = responseModel.getResponse();
                        if (requestModel != null){
                            PageInfo pageInfo = new PageInfo(requestModel);
                            data.setValue(pageInfo);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                AppLog.i(" onFailureCall = " + t);
                data.setValue(null);
            }
         });
        return data;
    }*/
}

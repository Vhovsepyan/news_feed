package com.example.vahe.newsfeed.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.NetworkState;
import com.example.vahe.newsfeed.model.PageInfo;
import com.example.vahe.newsfeed.model.request.BaseResponseModel;
import com.example.vahe.newsfeed.model.request.PageInfoResponseModel;
import com.example.vahe.newsfeed.repository.ArticleRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FeedDataSource extends PageKeyedDataSource<Long, Article> {

    private static final String TAG = FeedDataSource.class.getSimpleName();

    private ArticleRepository articleRepository;

    private MutableLiveData networkState;
    private MutableLiveData initialLoading;

    public FeedDataSource(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
        networkState = new MutableLiveData();
        initialLoading = new MutableLiveData();
    }


    public MutableLiveData getNetworkState() {
        return networkState;
    }

    public MutableLiveData getInitialLoading() {
        return initialLoading;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params,
                            @NonNull LoadInitialCallback<Long, Article> callback) {

        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);

        articleRepository.getNewsPageInfo(1, params.requestedLoadSize)
                .enqueue(new Callback<BaseResponseModel<PageInfoResponseModel>>() {
                    @Override
                    public void onResponse(Call<BaseResponseModel<PageInfoResponseModel>> call, Response<BaseResponseModel<PageInfoResponseModel>> response) {
                        if (response.isSuccessful()) {
                            PageInfo pageInfo = new PageInfo(response.body().getResponse());
                            callback.onResult(pageInfo.getArticles(), null, 2l);
                            initialLoading.postValue(NetworkState.LOADED);
                            networkState.postValue(NetworkState.LOADED);

                        } else {
                            initialLoading.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                            networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponseModel<PageInfoResponseModel>> call, Throwable t) {
                        String errorMessage = t == null ? "unknown error" : t.getMessage();
                        networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params,
                           @NonNull LoadCallback<Long, Article> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params,
                          @NonNull LoadCallback<Long, Article> callback) {

        Log.i(TAG, "Loading Rang " + params.key + " Count " + params.requestedLoadSize);

        networkState.postValue(NetworkState.LOADING);

        articleRepository.getNewsPageInfo(params.key, params.requestedLoadSize).enqueue(new Callback<BaseResponseModel<PageInfoResponseModel>>() {
            @Override
            public void onResponse(Call<BaseResponseModel<PageInfoResponseModel>> call, Response<BaseResponseModel<PageInfoResponseModel>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    long total = response.body().getResponse().getTotal();
                    long nextKey = (params.key == total) ? null : params.key + 1;
                    PageInfo pageInfo = new PageInfo(response.body().getResponse());
                    callback.onResult(pageInfo.getArticles(), nextKey);
                    networkState.postValue(NetworkState.LOADED);

                } else
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
            }

            @Override
            public void onFailure(Call<BaseResponseModel<PageInfoResponseModel>> call, Throwable t) {
                String errorMessage = t == null ? "unknown error" : t.getMessage();
                networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
            }
        });
    }
}

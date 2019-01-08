package com.example.vahe.newsfeed.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.vahe.newsfeed.dao.ArticleDao;
import com.example.vahe.newsfeed.executors.ExecutorService;
import com.example.vahe.newsfeed.executors.ExecutorType;
import com.example.vahe.newsfeed.http.RestApi;
import com.example.vahe.newsfeed.http.RestApiFactory;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.BaseObject;
import com.example.vahe.newsfeed.model.entity.ArticleTable;
import com.example.vahe.newsfeed.model.request.ArticleResponseModel;
import com.example.vahe.newsfeed.model.request.BaseResponseModel;
import com.example.vahe.newsfeed.model.request.PageInfoResponseModel;
import com.example.vahe.newsfeed.utils.AppLog;

import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepositoryImpl<T extends BaseObject> implements ArticleRepository {

    private ArticleDao articleDao;
    private RestApi restApi;

    private MutableLiveData<List<T>> objectList;
    private MutableLiveData<T> object = new MutableLiveData<>();

    public ArticleRepositoryImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }


    @Override
    public Call<BaseResponseModel<PageInfoResponseModel>> getNewsPageInfo(long page, long pageSize) {
        return getRestApi().getNewsPageInfo(page, pageSize);
    }

    @Override
    public LiveData<Article> getPageById(String id, String showFields, String apiKey) {
        MutableLiveData<Article> articleMutableLiveData = new MutableLiveData<>();
        getRestApi().getPageById(id, showFields, apiKey).enqueue(new Callback<BaseResponseModel<ArticleResponseModel>>() {
            @Override
            public void onResponse(Call<BaseResponseModel<ArticleResponseModel>> call, Response<BaseResponseModel<ArticleResponseModel>> response) {
                Article article = new Article(response.body().getResponse().getContent());
                articleMutableLiveData.postValue(article);

            }

            @Override
            public void onFailure(Call<BaseResponseModel<ArticleResponseModel>> call, Throwable t) {
                AppLog.i("getPageById id = " + id + " onFailure ");
            }
        });
        return articleMutableLiveData;
    }

    @Override
    public void insert(Article items) {
        getExecutor(ExecutorType.DB_COMMUNICATION).execute(() -> {
            articleDao.insert(new ArticleTable(items));
        });
    }

    @Override
    public void update(Article items) {
        getExecutor(ExecutorType.DB_COMMUNICATION).execute(() -> {
            articleDao.update(new ArticleTable(items));
        });

    }

    @Override
    public void delete(Article items) {
        getExecutor(ExecutorType.DB_COMMUNICATION).execute(() -> {
            articleDao.delete(new ArticleTable(items));
        });
    }

    private Executor getExecutor(@ExecutorType int type) {
        return ExecutorService.getExecutor(type);
    }

    public RestApi getRestApi() {
        if(restApi == null) {
            restApi = RestApiFactory.create();
        }
        return restApi;
    }

}

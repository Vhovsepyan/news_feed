package com.example.vahe.newsfeed.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.vahe.newsfeed.dao.ArticleDao;
import com.example.vahe.newsfeed.datasource.factory.FeedDataFactory;
import com.example.vahe.newsfeed.executors.ExecutorService;
import com.example.vahe.newsfeed.executors.ExecutorType;
import com.example.vahe.newsfeed.http.RestApi;
import com.example.vahe.newsfeed.http.RestApiFactory;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.BaseObject;
import com.example.vahe.newsfeed.model.NetworkState;
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

    private LiveData<NetworkState> networkState;
    private LiveData<PagedList<Article>> articleLiveData;

    public ArticleRepositoryImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
        init();
    }

    private void init() {

        FeedDataFactory feedDataFactory = new FeedDataFactory(this);
        networkState = Transformations.switchMap(feedDataFactory.getMutableLiveData(),
                dataSource -> dataSource.getNetworkState());

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(20)
                        .setPageSize(20).build();

        articleLiveData = (new LivePagedListBuilder(feedDataFactory, pagedListConfig))
                .setFetchExecutor(getExecutor(ExecutorType.SERVER_COMMUNICATION))
                .build();
    }

    @Override
    public Call<BaseResponseModel<PageInfoResponseModel>> getNewsPageInfo(long page, long pageSize) {
        return getRestApi().getNewsPageInfo(page, pageSize);
    }

    @Override
    public LiveData<Article> getPageByApiUrl(String apiUrl, String showFields) {
        MutableLiveData<Article> articleMutableLiveData = new MutableLiveData<>();
        getRestApi().getPageByApiUrl(apiUrl, showFields).enqueue(new Callback<BaseResponseModel<ArticleResponseModel>>() {
            @Override
            public void onResponse(Call<BaseResponseModel<ArticleResponseModel>> call, Response<BaseResponseModel<ArticleResponseModel>> response) {
                Article article = new Article(response.body().getResponse().getContent());
                articleMutableLiveData.postValue(article);

            }

            @Override
            public void onFailure(Call<BaseResponseModel<ArticleResponseModel>> call, Throwable t) {
                AppLog.i("getPageByApiUrl id = " + apiUrl + " onFailure ");
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

    private RestApi getRestApi() {
        if (restApi == null) {
            restApi = RestApiFactory.create();
        }
        return restApi;
    }

    public LiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    public LiveData<PagedList<Article>> getArticleLiveData() {
        return articleLiveData;
    }
}

package com.example.vahe.newsfeed.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.vahe.newsfeed.dao.ArticleDao;
import com.example.vahe.newsfeed.executors.ExecutorService;
import com.example.vahe.newsfeed.executors.ExecutorType;
import com.example.vahe.newsfeed.http.RequestHelper;
import com.example.vahe.newsfeed.listener.OnListReadyListener;
import com.example.vahe.newsfeed.listener.OnObjectReadyListener;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.BaseObject;
import com.example.vahe.newsfeed.model.PageInfo;
import com.example.vahe.newsfeed.model.entity.ArticleTable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class ArticleRepositoryImpl<T extends BaseObject> implements ArticleRepository {

    private RequestHelper requestHelper;
    private ArticleDao articleDao;

    private MutableLiveData<List<T>> objectList;
    private MutableLiveData<T> object = new MutableLiveData<>();

    public ArticleRepositoryImpl(ArticleDao articleDao, RequestHelper requestHelper) {
        this.requestHelper = requestHelper;
        this.articleDao = articleDao;
    }

    @Override
    public LiveData<PageInfo> getPageInfoFromApi(String url) {
        return requestHelper.getPageInfo();
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


    private OnObjectReadyListener<T> onObjectReadyListener = new OnObjectReadyListener<T>() {
        @Override
        public void onReady(T obj) {
            object.setValue(obj);
        }
    };
}

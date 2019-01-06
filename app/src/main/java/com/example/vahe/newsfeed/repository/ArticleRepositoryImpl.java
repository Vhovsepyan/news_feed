package com.example.vahe.newsfeed.repository;

import android.arch.lifecycle.MutableLiveData;

import com.example.vahe.newsfeed.dao.ArticleDao;
import com.example.vahe.newsfeed.executors.ExecutorService;
import com.example.vahe.newsfeed.executors.ExecutorType;
import com.example.vahe.newsfeed.http.RequestHelper;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.BaseObject;
import com.example.vahe.newsfeed.model.entity.ArticleTable;

import java.util.List;
import java.util.concurrent.Executor;

public class ArticleRepositoryImpl<T extends BaseObject> implements ArticleRepository {

    private ArticleDao articleDao;

    private MutableLiveData<List<T>> objectList;
    private MutableLiveData<T> object = new MutableLiveData<>();

    public ArticleRepositoryImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
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

}

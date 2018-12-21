package com.example.vahe.newsfeed.repository;

import com.example.vahe.newsfeed.dao.ArticleDao;
import com.example.vahe.newsfeed.executors.ExecutorService;
import com.example.vahe.newsfeed.executors.ExecutorType;
import com.example.vahe.newsfeed.http.RequestHelper;
import com.example.vahe.newsfeed.listener.OnListReadyListener;
import com.example.vahe.newsfeed.listener.OnObjectReadyListener;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.PageInfo;
import com.example.vahe.newsfeed.model.entity.ArticleTable;
import com.example.vahe.newsfeed.model.request.PageInfoRequestModel;
import com.example.vahe.newsfeed.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class ArticleRepositoryImpl implements ArticleRepository {

    private RequestHelper requestHelper;
    private ArticleDao articleDao;

    public ArticleRepositoryImpl(ArticleDao articleDao, RequestHelper requestHelper) {
        this.requestHelper = requestHelper;
        this.articleDao = articleDao;
    }

    @Override
    public void getPageInfoFromApi(String url, OnObjectReadyListener<PageInfo> onListReadyListener) {
        getExecutor(ExecutorType.SERVER_COMMUNICATION).execute(() -> {
            String data = requestHelper.getArticles(url);
            PageInfo pageInfo = null;
            try {
                JSONObject jsonObject = new JSONObject(data);
                Gson gson = new GsonBuilder().create();
                Type collectionType = new TypeToken<PageInfoRequestModel>() {
                }.getType();
                PageInfoRequestModel info = gson.fromJson(jsonObject.getJSONObject(Constants.RESPONSE_KEY).toString(), collectionType);
                if (info != null) {
                    pageInfo = new PageInfo(info);
                    PageInfo finalPageInfo = pageInfo;
                    getExecutor(ExecutorType.MAIN).execute(() -> {
                        onListReadyListener.onReady(finalPageInfo);
                    });
                }

            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public void getArticlesFromApi(String url, OnListReadyListener<Article> onListReadyListener) {
        getExecutor(ExecutorType.SERVER_COMMUNICATION).execute(() -> {
            String data = requestHelper.getArticles(url);
            PageInfo pageInfo = null;
            try {

                JSONObject jsonObject = new JSONObject(data);
                Gson gson = new GsonBuilder().create();
                Type collectionType = new TypeToken<PageInfoRequestModel>() {
                }.getType();
                PageInfoRequestModel info = gson.fromJson(jsonObject.getJSONObject(Constants.RESPONSE_KEY).toString(), collectionType);
                if (info != null) {
                    pageInfo = new PageInfo(info);
                    PageInfo finalPageInfo = pageInfo;
                    getExecutor(ExecutorType.MAIN).execute(() -> {
                        onListReadyListener.onReady(finalPageInfo.getArticles());
                    });
                }

            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        });
    }


    @Override
    public void getArticlesFromDB(OnListReadyListener<Article> onListReadyListener) {
        getExecutor(ExecutorType.DB_COMMUNICATION).execute(() -> {
            List<Article> articleList = new ArrayList<>();
            List<ArticleTable> articles = articleDao.getPinnedArticles();
            if (articles != null && !articles.isEmpty()) {
                for (int i = 0; i < articles.size(); i++) {
                    ArticleTable articleTable = articles.get(i);
                    articleList.add(new Article(articleTable));
                }
            }
            getExecutor(ExecutorType.MAIN).execute(() -> {
                onListReadyListener.onReady(articleList);
            });
        });
    }

    @Override
    public void getArticleById(String id, OnObjectReadyListener<Article> onReadyListener) {
        getExecutor(ExecutorType.DB_COMMUNICATION).execute(() -> {
            ArticleTable articleTable = articleDao.getArticleById(id);
            if (articleTable != null) {
                Article article = new Article(articleTable);
                getExecutor(ExecutorType.MAIN).execute(() -> {
                    onReadyListener.onReady(article);
                });
            }
        });
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

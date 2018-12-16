package com.example.vahe.newsfeed.repository;

import com.example.vahe.newsfeed.dao.ArticleDao;
import com.example.vahe.newsfeed.http.RequestHelper;
import com.example.vahe.newsfeed.http.RequestHelperImpl;
import com.example.vahe.newsfeed.listener.OnListReadyListener;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.PageInfo;
import com.example.vahe.newsfeed.model.entity.ArticleTable;
import com.example.vahe.newsfeed.model.request.PageInfoRequestModel;
import com.example.vahe.newsfeed.providers.ExecutorService;
import com.example.vahe.newsfeed.providers.ExecutorType;
import com.example.vahe.newsfeed.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Executor;

public class NewsRepositoryImpl implements NewsRepository {
    private RequestHelper requestHelper;

    private ArticleDao articleDao;

    public NewsRepositoryImpl(ArticleDao articleDao) {
        this.requestHelper = new RequestHelperImpl();
        this.articleDao = articleDao;
    }

    @Override
    public PageInfo getPageInfoFromApi() {
        String data = requestHelper.getNews(Constants.SEARCH_URL);
        PageInfo pageInfo = null;
        try {

            JSONObject jsonObject = new JSONObject(data);
            Gson gson = new GsonBuilder().create();
            Type collectionType = new TypeToken<PageInfoRequestModel>() {}.getType();
            PageInfoRequestModel info = gson.fromJson(jsonObject.getJSONObject(Constants.RESPONSE_KEY).toString(), collectionType);
            if (info != null){
                pageInfo = new PageInfo(info);
            }

        } catch (JSONException ex){
            ex.printStackTrace();
        }
        return pageInfo;
    }


    @Override
    public void getArticlesFromDB(OnListReadyListener onListReadyListener) {
        getExecutor(ExecutorType.DB_COMMUNICATION).execute(()->{
            List<Article> articles = articleDao.getPinnedArticles();
            onListReadyListener.onReady(articles);
        });
    }

    @Override
    public void insert(Article items) {
        getExecutor(ExecutorType.DB_COMMUNICATION).execute(()->{
            articleDao.insert(new ArticleTable(items));
        });
    }

    @Override
    public void update(Article items) {
        getExecutor(ExecutorType.DB_COMMUNICATION).execute(()->{
            articleDao.update(new ArticleTable(items));
        });

    }

    @Override
    public void delete(Article items) {
        getExecutor(ExecutorType.DB_COMMUNICATION).execute(()-> {
            articleDao.delete(new ArticleTable(items));
        });
    }

    private Executor getExecutor(@ExecutorType int type){
        return ExecutorService.getExecutor(type);
    }
}

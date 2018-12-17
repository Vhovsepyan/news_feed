package com.example.vahe.newsfeed.screens.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.executors.ExecutorType;
import com.example.vahe.newsfeed.listener.BaseClickListener;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.repository.NewsRepository;
import com.example.vahe.newsfeed.screens.BaseAdapter;
import com.example.vahe.newsfeed.screens.BaseVM;
import com.example.vahe.newsfeed.screens.info.ArticleInfoFragment;
import com.example.vahe.newsfeed.utils.ArticleUrlBuilder;
import com.example.vahe.newsfeed.utils.Constants;
import com.example.vahe.newsfeed.utils.SharedPrefs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.inject.Inject;

import androidx.navigation.NavController;

public class PageInfoVM extends BaseVM {
    private CopyOnWriteArrayList<Article> articles = new CopyOnWriteArrayList<>();
    private List<Article> pinnedArticles = new ArrayList<>();
    public BaseAdapter adapter;
    public BaseAdapter pinnedAdapter;

    @Inject
    public NewsRepository newsRepository;

    public PageInfoVM(NavController navController, Context appContext) {
        super(navController, appContext);
        LayoutInflater inflater = LayoutInflater.from(appContext);
        adapter = new BaseAdapter(inflater, baseClickListener);
        pinnedAdapter = new BaseAdapter(inflater, baseClickListener);
    }

    @Override
    protected void init() {
        getPinnedArticles();
        getNewsFromAPI();
    }

    private void getNewsFromAPI() {
        String url = new ArticleUrlBuilder().build();
        newsRepository.getPageInfoFromApi(url, list -> {
            if (list != null) {
                articles.addAll(list);
                getExecutor(ExecutorType.MAIN).execute(() -> {
                    Article lastArticle = articles.get(0);
                    SharedPrefs.getInstance().putString(Constants.LAST_PUBLICATION_DATE, lastArticle.getWebPublicationDate());
                    adapter.setItems(articles);
                });
            }
        });
    }

    private void getPinnedArticles() {
        newsRepository.getArticlesFromDB(list -> {
            if (list != null) {
                pinnedArticles.addAll(list);
                getExecutor(ExecutorType.MAIN).execute(() -> {
                    pinnedAdapter.setItems(pinnedArticles);
                });
            }

        });

    }

    private BaseClickListener baseClickListener = obj -> {
        Article article = (Article) obj;
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArticleInfoFragment.BUNDLE_KEY_INFO, article);
        getNavController().navigate(R.id.infoFragment, bundle);
    };

    @Override
    protected void onDestroyView() {
        super.onDestroyView();
    }

}

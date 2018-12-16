package com.example.vahe.newsfeed.screens.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.listener.BaseClickListener;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.providers.ExecutorType;
import com.example.vahe.newsfeed.repository.NewsRepository;
import com.example.vahe.newsfeed.screens.BaseAdapter;
import com.example.vahe.newsfeed.screens.BaseVM;
import com.example.vahe.newsfeed.screens.info.ArticleInfoFragment;
import com.example.vahe.newsfeed.utils.ArticleUrlBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import androidx.navigation.NavController;

public class PageInfoVM extends BaseVM {
    private List<Article> articles = new ArrayList<>();
    private List<Article> pinnedArticles = new ArrayList<>();
    public BaseAdapter adapter;
    public BaseAdapter pinnedAdapter;
    ScheduledExecutorService executorService = (ScheduledExecutorService) getExecutor(ExecutorType.BACKGROUND);

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
        startBackgroundTask();
    }

    private void getNewsFromAPI() {
        String url = new ArticleUrlBuilder().build();
        newsRepository.getPageInfoFromApi(url, list -> {
            if (list != null) {
                articles.addAll(list);
                getExecutor(ExecutorType.MAIN).execute(() -> {
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


    private void startBackgroundTask() {
        executorService.schedule(() -> {
            String publicationDate = articles.get(0).getWebPublicationDate();
            String url = new ArticleUrlBuilder()
                    .addFromDate(publicationDate)
                    .build();
            newsRepository.getPageInfoFromApi(url, list -> {
                List<Article> tempArticles = new ArrayList<>(list);
                if (tempArticles.size() > 0) {
                    tempArticles.addAll(articles);
                    articles.clear();
                    articles.addAll(tempArticles);
                    getExecutor(ExecutorType.MAIN).execute(() -> {
                        adapter.notifyDataSetChanged();
                    });
                }
            });

            startBackgroundTask();

        }, 30, TimeUnit.SECONDS);
    }

    @Override
    protected void onDestroyView() {
        super.onDestroyView();
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}

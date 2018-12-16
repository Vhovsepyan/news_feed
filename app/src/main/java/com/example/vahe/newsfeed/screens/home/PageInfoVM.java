package com.example.vahe.newsfeed.screens.home;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.listener.OnListReadyListener;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.BaseObject;
import com.example.vahe.newsfeed.model.PageInfo;
import com.example.vahe.newsfeed.providers.ExecutorType;
import com.example.vahe.newsfeed.repository.NewsRepository;
import com.example.vahe.newsfeed.screens.BaseAdapter;
import com.example.vahe.newsfeed.listener.BaseClickListener;
import com.example.vahe.newsfeed.screens.BaseVM;
import com.example.vahe.newsfeed.screens.info.ArticleInfoFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.navigation.NavController;

public class PageInfoVM extends BaseVM {
    public ObservableField<PageInfo> pageInfo = new ObservableField<>();
    private List<Article> articles = new ArrayList<>();
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

    @Override
    protected boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    private void getNewsFromAPI() {
        getExecutor(ExecutorType.SERVER_COMMUNICATION).execute(() -> {
            PageInfo page = newsRepository.getPageInfoFromApi();
            if (page != null) {
                pageInfo.set(page);
                articles.addAll(page.getArticles());
                getExecutor(ExecutorType.MAIN).execute(() -> {
                    adapter.setItems(articles);
                });
            }
        });
    }

    private void getPinnedArticles() {
        newsRepository.getArticlesFromDB(list -> {
            pinnedArticles.addAll(list);
            getExecutor(ExecutorType.MAIN).execute(() -> {
                pinnedAdapter.setItems(pinnedArticles);
            });

        });

    }

    private BaseClickListener baseClickListener = new BaseClickListener() {
        @Override
        public void onItemClickListener(BaseObject obj) {
            Article article = (Article) obj;
            Bundle bundle = new Bundle();
            bundle.putParcelable(ArticleInfoFragment.BUNDLE_KEY_INFO, article);
            getNavController().navigate(R.id.infoFragment, bundle);
        }
    };
}

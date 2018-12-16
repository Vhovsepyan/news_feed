package com.example.vahe.newsfeed.screens.home;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.BaseObject;
import com.example.vahe.newsfeed.model.PageInfo;
import com.example.vahe.newsfeed.providers.ExecutorType;
import com.example.vahe.newsfeed.repository.NewsRepository;
import com.example.vahe.newsfeed.repository.NewsRepositoryImpl;
import com.example.vahe.newsfeed.screens.BaseClickListener;
import com.example.vahe.newsfeed.screens.BaseVM;
import com.example.vahe.newsfeed.screens.info.ArticleInfoFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.navigation.NavController;

public class PageInfoVM extends BaseVM {
    public ObservableField<PageInfo> pageInfo = new ObservableField<>();
    private List<Article> articles = new ArrayList<>();
    private NewsRepository newsRepository;
    public NewsItemAdapter adapter;

    public PageInfoVM(NavController navController, Context appContext) {
        super(navController, appContext);
        LayoutInflater inflater = LayoutInflater.from(appContext);
        adapter = new NewsItemAdapter(inflater, baseClickListener );
        newsRepository = new NewsRepositoryImpl();
        getNews();
    }

    private void getNews(){
        getExecutor(ExecutorType.BACKGROUND).execute(()->{
            PageInfo page = newsRepository.getPageInfoFromApi();
            pageInfo.set(page);
            articles.addAll(page.getArticles());
            getExecutor(ExecutorType.MAIN).execute(()->{
                adapter.setItems(articles);
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

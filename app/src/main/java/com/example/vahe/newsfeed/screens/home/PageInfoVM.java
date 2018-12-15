package com.example.vahe.newsfeed.screens.home;

import android.databinding.ObservableField;

import com.example.vahe.newsfeed.model.NewsItem;
import com.example.vahe.newsfeed.model.PageInfo;
import com.example.vahe.newsfeed.providers.ExecutorType;
import com.example.vahe.newsfeed.repository.NewsRepository;
import com.example.vahe.newsfeed.repository.NewsRepositoryImpl;
import com.example.vahe.newsfeed.screens.BaseVM;

import java.util.List;

public class PageInfoVM extends BaseVM {
    public ObservableField<PageInfo> pageInfo = new ObservableField<>();
    private List<NewsItem> newsItems;
    private NewsRepository newsRepository;
    private NewsItemAdapter adapter;

    public PageInfoVM() {
        newsRepository = new NewsRepositoryImpl();
        getNews();
        adapter = new NewsItemAdapter();
    }

    private void getNews(){
        getExecutor(ExecutorType.BACKGROUND).execute(()->{
            PageInfo page = newsRepository.getPageInfoFromApi();
            pageInfo.set(page);
            newsItems.addAll(page.getNewsItems());
        });
    }
}

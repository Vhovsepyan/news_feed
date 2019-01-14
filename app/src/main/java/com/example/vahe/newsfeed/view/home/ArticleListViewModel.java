package com.example.vahe.newsfeed.view.home;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.databinding.ObservableBoolean;
import android.support.annotation.IntDef;

import com.example.vahe.newsfeed.NewsFeedApp;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.NetworkState;
import com.example.vahe.newsfeed.repository.ArticleRepository;
import com.example.vahe.newsfeed.view.BaseVM;

import javax.inject.Inject;

public class ArticleListViewModel extends BaseVM {

    public ObservableBoolean isListViewMode = new ObservableBoolean(true);
    public ObservableBoolean isPinnedVisible = new ObservableBoolean(false);
    public ObservableBoolean isProgessBarVisible = new ObservableBoolean(false);

    private LiveData<NetworkState> networkState;
    private LiveData<PagedList<Article>> articleLiveData;


    @Inject
    public ArticleRepository articleRepository;

    public ArticleListViewModel(Application app) {
        super(app);
        ((NewsFeedApp) app).appComponent().inject(this);
        networkState = articleRepository.getNetworkState();
        articleLiveData = articleRepository.getArticleLiveData();

    }


    @IntDef({RecyclerViewSwitchModeDef.VERTICAL, RecyclerViewSwitchModeDef.PINTEREST})
    public @interface RecyclerViewSwitchModeDef {
        int VERTICAL = 0;
        int PINTEREST = 1;
    }


    public LiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    public LiveData<PagedList<Article>> getArticleLiveData() {
        return articleLiveData;
    }

}

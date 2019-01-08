package com.example.vahe.newsfeed.view.home;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.databinding.ObservableBoolean;
import android.support.annotation.IntDef;

import com.example.vahe.newsfeed.NewsFeedApp;
import com.example.vahe.newsfeed.datasource.factory.FeedDataFactory;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.repository.ArticleRepository;
import com.example.vahe.newsfeed.model.NetworkState;
import com.example.vahe.newsfeed.view.BaseVM;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class ArticleListViewModel extends BaseVM {

    public ObservableBoolean isListViewMode = new ObservableBoolean(true);
    public ObservableBoolean isPinnedVisible = new ObservableBoolean(false);
    public ObservableBoolean isProgessBarVisible = new ObservableBoolean(false);

    private Executor executor;
    private LiveData<NetworkState> networkState;
    private LiveData<PagedList<Article>> articleLiveData;


    @Inject
    public ArticleRepository articleRepository;

    public ArticleListViewModel(Application app) {
        super(app);
        ((NewsFeedApp)app).appComponent().inject(this);
        init();
    }


    private void init() {
        executor = Executors.newFixedThreadPool(5);

        FeedDataFactory feedDataFactory = new FeedDataFactory(articleRepository);
        networkState = Transformations.switchMap(feedDataFactory.getMutableLiveData(),
                dataSource -> dataSource.getNetworkState());

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(20)
                        .setPageSize(20).build();

        articleLiveData = (new LivePagedListBuilder(feedDataFactory, pagedListConfig))
                .setFetchExecutor(executor)
                .build();
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

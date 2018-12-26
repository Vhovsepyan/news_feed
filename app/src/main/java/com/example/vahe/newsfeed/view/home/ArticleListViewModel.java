package com.example.vahe.newsfeed.view.home;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.IntDef;

import com.example.vahe.newsfeed.NewsFeedApp;
import com.example.vahe.newsfeed.listener.BaseClickListener;
import com.example.vahe.newsfeed.listener.OnLoadMoreListener;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.PageInfo;
import com.example.vahe.newsfeed.repository.ArticleRepository;
import com.example.vahe.newsfeed.view.adapter.BaseAdapter;
import com.example.vahe.newsfeed.view.BaseVM;
import com.example.vahe.newsfeed.view.info.ArticleInfoFragment;
import com.example.vahe.newsfeed.utils.AppLog;
import com.example.vahe.newsfeed.utils.ArticleUrlBuilder;
import com.example.vahe.newsfeed.utils.Constants;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.inject.Inject;

public class ArticleListViewModel extends BaseVM {

    private final LiveData<PageInfo> pageInfoLiveData;
    public ObservableBoolean isListViewMode = new ObservableBoolean(true);
    public ObservableBoolean isPinnedVisible = new ObservableBoolean(false);
    public ObservableBoolean isProgessBarVisible = new ObservableBoolean(true);
    public ObservableField<BaseAdapter> baseAdapterObservableField = new ObservableField<>();
    private CopyOnWriteArrayList<Article> articles = new CopyOnWriteArrayList<>();
    private LiveData<List<Article>> pinnedArticles;
    public BaseAdapter adapter;
    public BaseAdapter pinnedAdapter;
    private PageInfo pageInfo;

    @Inject
    public ArticleRepository articleRepository;

    public ArticleListViewModel(Application app) {
        super(app);
        NewsFeedApp newsFeedApp = (NewsFeedApp) app;
        newsFeedApp.appComponent().inject(this);
        pageInfoLiveData = articleRepository.getPageInfoFromApi("");
    }

    @IntDef({RecyclerViewSwitchModeDef.VERTICAL, RecyclerViewSwitchModeDef.PINTEREST})
    public @interface RecyclerViewSwitchModeDef {
        int VERTICAL = 0;
        int PINTEREST = 1;
    }

    public OnLoadMoreListener loadMoreListener = new OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            String url = new ArticleUrlBuilder()
                    .addPage(pageInfo.getCurrentPage() + 1)
                    .addUseDate(Constants.USE_DATE_PUBLISHED)
                    .addOrderDate(Constants.ORDER_DATE_PUBLISHED)
                    .build();
            AppLog.i(" load   More ");
        }
    };

    private BaseClickListener baseClickListener = obj -> {
        Article article = (Article) obj;
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArticleInfoFragment.BUNDLE_ARTICLE_ID_KEY_INFO, article);
//        getNavController().navigate(R.id.infoFragment, bundle);
    };

    public LiveData<PageInfo> getPageInfo(){
        return pageInfoLiveData;
    }
}

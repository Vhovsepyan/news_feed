package com.example.vahe.newsfeed.screens.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.executors.ExecutorType;
import com.example.vahe.newsfeed.listener.BaseClickListener;
import com.example.vahe.newsfeed.listener.OnLoadMoreListener;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.PageInfo;
import com.example.vahe.newsfeed.repository.ArticleRepository;
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
    private PageInfo pageInfo;

    @Inject
    public ArticleRepository articleRepository;

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
        String url = new ArticleUrlBuilder()
                .addUseDate(Constants.USE_DATE_PUBLISHED)
                .addOrderBy(Constants.ORDER_BY_NEWEST)
                .addOrderDate(Constants.ORDER_DATE_PUBLISHED)
                .build();
        articleRepository.getPageInfoFromApi(url, pageInfo -> {
            if (pageInfo != null) {
                this.pageInfo = pageInfo;
                articles.addAll(pageInfo.getArticles());
                getExecutor(ExecutorType.MAIN).execute(() -> {
                    Article lastArticle = articles.get(0);
                    SharedPrefs.getInstance().putString(Constants.LAST_PUBLICATION_DATE, lastArticle.getWebPublicationDate());
                    adapter.setItems(articles);
                });
            }
        });
    }

    private void getPinnedArticles() {
        articleRepository.getArticlesFromDB(list -> {
            if (list != null) {
                pinnedArticles.addAll(list);
                getExecutor(ExecutorType.MAIN).execute(() -> {
                    pinnedAdapter.setItems(pinnedArticles);
                });
            }

        });

    }

    public OnLoadMoreListener loadMoreListener = new OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            String url = new ArticleUrlBuilder()
                    .addPage(pageInfo.getCurrentPage() + 1)
                    .addUseDate(Constants.USE_DATE_PUBLISHED)
                    .addOrderDate(Constants.ORDER_DATE_PUBLISHED)
                    .build();
            articleRepository.getPageInfoFromApi(url, pageInfo -> {
                if (pageInfo != null) {
                    PageInfoVM.this.pageInfo = pageInfo;
                    articles.addAll(pageInfo.getArticles());
                    getExecutor(ExecutorType.MAIN).execute(() -> {
                        adapter.setItems(articles);
                    });
                }
            });
        }
    };

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

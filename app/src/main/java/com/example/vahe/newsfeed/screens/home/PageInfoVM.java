package com.example.vahe.newsfeed.screens.home;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

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

public class PageInfoVM extends BaseVM {
    public ObservableBoolean isListViewMode = new ObservableBoolean(true);
    public ObservableBoolean isPinnedVisible = new ObservableBoolean(false);
    public ObservableBoolean isProgessBarVisible = new ObservableBoolean(true);
    public ObservableField<BaseAdapter> baseAdapterObservableField = new ObservableField<>();
    private CopyOnWriteArrayList<Article> articles = new CopyOnWriteArrayList<>();
    private List<Article> pinnedArticles = new ArrayList<>();
    public BaseAdapter adapter;
    public BaseAdapter pinnedAdapter;
    private PageInfo pageInfo;
    private LayoutInflater inflater;

    @Inject
    public ArticleRepository articleRepository;

    public PageInfoVM(Context appContext) {
        super(appContext);
        inflater = LayoutInflater.from(appContext);
        int mode = getPreferences().getInt(Constants.HOME_VIEW_MODE_KEY, 0);
        adapter = new BaseAdapter(inflater, baseClickListener, mode);
        baseAdapterObservableField.set(adapter);
        pinnedAdapter = new BaseAdapter(inflater, baseClickListener, mode);
        isListViewMode.set(mode == RecyclerViewSwitchModeDef.VERTICAL);
    }

    @Override
    protected void init() {
        isProgessBarVisible.set(true);
        getNewsFromAPI();
    }

    @Override
    protected void onViewCreated(View view, Bundle bundle, ViewDataBinding binding) {
        super.onViewCreated(view, bundle, binding);
        getPinnedArticles();
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
                    SharedPrefs.getInstance().putString(Constants.PREF_LAST_PUBLICATION_DATE, lastArticle.getWebPublicationDate());
                    adapter.setItems(articles);
                    isProgessBarVisible.set(false);
                });
            }
        });
    }

    private void getPinnedArticles() {
        articleRepository.getArticlesFromDB(list -> {
            if (list != null) {
                pinnedArticles.clear();
                pinnedArticles.addAll(list);
                isPinnedVisible.set(pinnedArticles.size() > 0);
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
        bundle.putParcelable(ArticleInfoFragment.BUNDLE_ARTICLE_ID_KEY_INFO, article);
        getNavController().navigate(R.id.infoFragment, bundle);
    };

    @Override
    protected void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    protected void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.page_info_menu, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem menuItem = menu.findItem(R.id.action_change_mode);
        if (isListViewMode.get()) {
            menuItem.setTitle(getString(R.string.stagger_text));
        } else {
            menuItem.setTitle(getString(R.string.list_text));
        }
    }

    @Override
    protected void onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_change_mode: {
                handleChangeModeAction(item, isListViewMode.get());
            }
        }
    }

    private void handleChangeModeAction(MenuItem menuItem, boolean isListMode) {
        int viewMode;
        if (isListMode) {
            viewMode = RecyclerViewSwitchModeDef.PINTEREST;
            isListViewMode.set(false);
            menuItem.setTitle(getString(R.string.list_text));
        } else {
            viewMode = RecyclerViewSwitchModeDef.VERTICAL;
            isListViewMode.set(true);
            menuItem.setTitle(getString(R.string.stagger_text));
        }
        getPreferences().putInt(Constants.HOME_VIEW_MODE_KEY, viewMode);
        adapter = new BaseAdapter(inflater, baseClickListener, viewMode);
        adapter.setItems(articles);
        baseAdapterObservableField.set(adapter);
    }

    @IntDef({RecyclerViewSwitchModeDef.VERTICAL, RecyclerViewSwitchModeDef.PINTEREST})
    public @interface RecyclerViewSwitchModeDef {
        int VERTICAL = 0;
        int PINTEREST = 1;
    }
}

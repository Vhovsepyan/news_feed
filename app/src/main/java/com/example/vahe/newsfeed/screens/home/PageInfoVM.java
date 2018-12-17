package com.example.vahe.newsfeed.screens.home;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.executors.ExecutorType;
import com.example.vahe.newsfeed.listener.BaseClickListener;
import com.example.vahe.newsfeed.listener.OnLoadMoreListener;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.PageInfo;
import com.example.vahe.newsfeed.model.request.PageInfoRequestModel;
import com.example.vahe.newsfeed.repository.ArticleRepository;
import com.example.vahe.newsfeed.screens.BaseAdapter;
import com.example.vahe.newsfeed.screens.BaseVM;
import com.example.vahe.newsfeed.screens.info.ArticleInfoFragment;
import com.example.vahe.newsfeed.utils.ArticleUrlBuilder;
import com.example.vahe.newsfeed.utils.Constants;
import com.example.vahe.newsfeed.utils.SharedPrefs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.inject.Inject;

import androidx.navigation.NavController;

public class PageInfoVM extends BaseVM {
    public ObservableBoolean isListViewMode = new ObservableBoolean(true);
    public ObservableField<BaseAdapter> baseAdapterObservableField = new ObservableField<>();
    private CopyOnWriteArrayList<Article> articles = new CopyOnWriteArrayList<>();
    private List<Article> pinnedArticles = new ArrayList<>();
    public BaseAdapter adapter;
    public BaseAdapter pinnedAdapter;
    private PageInfo pageInfo;
    private LayoutInflater inflater;

    @Inject
    public ArticleRepository articleRepository;

    public PageInfoVM(NavController navController, Context appContext) {
        super(navController, appContext);
        inflater = LayoutInflater.from(appContext);
        int mode = getPreferences().getInt(Constants.HOME_VIEW_MODE_KEY, 0);
        adapter = new BaseAdapter(inflater, baseClickListener, mode);
        baseAdapterObservableField.set(adapter);
        pinnedAdapter = new BaseAdapter(inflater, baseClickListener, mode);
        isListViewMode.set(mode == RecyclerViewSwitchModeDef.VERTICAL);
    }

    @Override
    protected void init() {
        getPinnedArticles();
        String savedData = getPreferences().getString(Constants.ARTICLES_JSON_DATA_KEY, "");
        if (TextUtils.isEmpty(savedData)){
            getNewsFromAPI();
        } else {
            handleSavedData(savedData);
        }
    }

    private void handleSavedData(String data){
        getExecutor(ExecutorType.BACKGROUND).execute(()->{
            try {
                JSONObject jsonObject = new JSONObject(data);
                Gson gson = new GsonBuilder().create();
                Type collectionType = new TypeToken<PageInfoRequestModel>() {
                }.getType();
                PageInfoRequestModel info = gson.fromJson(jsonObject.getJSONObject(Constants.RESPONSE_KEY).toString(), collectionType);
                if (info != null) {
                    pageInfo = new PageInfo(info);
                    articles.addAll(pageInfo.getArticles());
                    getExecutor(ExecutorType.MAIN).execute(()->{
                        adapter.setItems(articles);
                    });

                }

            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        });

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


    @Override
    protected void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.page_info_menu, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem menuItem = menu.findItem(R.id.action_pin);
        if (isListViewMode.get()) {
            menuItem.setTitle(getString(R.string.stagger_text));
        } else {
            menuItem.setTitle(getString(R.string.list_text));
        }
    }

    @Override
    protected void onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_pin: {
                handelPinAction(item, isListViewMode.get());
            }
        }
    }

    private void handelPinAction(MenuItem menuItem, boolean isListMode) {
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

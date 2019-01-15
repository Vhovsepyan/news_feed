package com.example.vahe.newsfeed.view.info;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.MenuItem;

import com.example.vahe.newsfeed.NewsFeedApp;
import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.repository.ArticleRepository;
import com.example.vahe.newsfeed.utils.UrlHelper;
import com.example.vahe.newsfeed.view.BaseVM;

import javax.inject.Inject;

public class ArticleVM extends BaseVM {

    @Inject
    public ArticleRepository articleRepository;
    private LiveData<Article> articleFromApiLiveData;
    private LiveData<Article> articleFromDBLiveData;

    public ObservableBoolean isProgessBarVisible = new ObservableBoolean(true);
    public ObservableField<Article> article = new ObservableField<>();

    public ArticleVM(Application app) {
        super(app);
        ((NewsFeedApp) app).appComponent().inject(this);
    }

    public void init(String id) {
        String apiUrl = UrlHelper.getArticleApiUrl(id);
        articleFromApiLiveData = articleRepository.getPageByApiUrl(apiUrl);
        articleFromDBLiveData = articleRepository.getArticleById(id);
    }

    public LiveData<Article> getArticleFromApiLiveData() {
        return articleFromApiLiveData;
    }

    public LiveData<Article> getArticleFromDBLiveData() {
        return articleFromDBLiveData;
    }

    public void setArticle(Article article) {
        if (this.article.get() != null) {
            article.setPinned(true);
        }
        this.article.set(article);
        isProgessBarVisible.set(false);
    }

    @Override
    public void onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_pin: {
                handelPinAction(item, article.get().isPinned());
            }
        }
    }

    private void handelPinAction(MenuItem menuItem, boolean isPinned) {
        if (isPinned) {
            article.get().setPinned(false);
            articleRepository.delete(article.get());
            menuItem.setTitle(getString(R.string.pin_text));
        } else {
            article.get().setPinned(true);
            articleRepository.insert(article.get());
            menuItem.setTitle(getString(R.string.pinned_text));
        }
    }
}

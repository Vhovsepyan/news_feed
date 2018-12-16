package com.example.vahe.newsfeed.screens.info;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.vahe.newsfeed.BR;
import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.repository.NewsRepository;
import com.example.vahe.newsfeed.screens.BaseVM;
import com.example.vahe.newsfeed.utils.AppLog;

import javax.inject.Inject;

import androidx.navigation.NavController;

public class ArticleVM extends BaseVM {

    @Inject
    public NewsRepository newsRepository;
    public Article article;

    public ArticleVM(NavController navController, Context appContext) {
        super(navController, appContext);
    }

    @Override
    protected void onViewCreated(View view, Bundle bundle, ViewDataBinding binding) {
        super.onViewCreated(view, bundle, binding);
        if (bundle != null && bundle.containsKey(ArticleInfoFragment.BUNDLE_KEY_INFO)) {
            Article article = bundle.getParcelable(ArticleInfoFragment.BUNDLE_KEY_INFO);
            setArticle(article);
            binding.setVariable(BR.articleItem, this.article);
            AppLog.i(" aaaaa " + article);
        }
    }

    @Override
    protected void init() {

    }

    @Bindable
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
        notifyPropertyChanged(BR.viewModel);
    }

    @Override
    protected void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.article_info_menu, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem menuItem = menu.findItem(R.id.action_pin);
        if (article.isPinned()) {
            menuItem.setTitle(getString(R.string.pinned_text));
        } else {
            menuItem.setTitle(getString(R.string.pin_text));
        }
    }

    @Override
    protected void onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_pin: {
                handelPinAction(item, article.isPinned());
            }
        }
    }

    private void handelPinAction(MenuItem menuItem, boolean isPinned) {
        if (isPinned) {
            article.setPinned(false);
            newsRepository.delete(article);
            menuItem.setTitle(getString(R.string.pin_text));
        } else {
            article.setPinned(true);
            newsRepository.insert(article);
            menuItem.setTitle(getString(R.string.pinned_text));
        }
    }
}

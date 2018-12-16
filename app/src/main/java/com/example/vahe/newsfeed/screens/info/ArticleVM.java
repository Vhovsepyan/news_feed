package com.example.vahe.newsfeed.screens.info;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
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
    public Article article ;

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
    protected boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_pin:{
                AppLog.i(" aaaa pinned ");
                article.setPinned(true);
                newsRepository.insert(article);
            }
        }
        return false;
    }
}

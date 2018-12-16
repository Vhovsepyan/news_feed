package com.example.vahe.newsfeed.screens.info;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;

import com.example.vahe.newsfeed.BR;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.screens.BaseVM;
import com.example.vahe.newsfeed.utils.AppLog;

import androidx.navigation.NavController;

public class ArticleVM extends BaseVM {

    public Article article ;

    public ArticleVM(NavController navController, Context appContext) {
        super(navController, appContext);
    }

    @Override
    protected void onViewCreated(View view, Bundle bundle, ViewDataBinding binding) {
        super.onViewCreated(view, bundle, binding);
        if (bundle != null && bundle.containsKey(ArticleInfoFragment.BUNDLE_KEY_INFO)) {
            Article article = bundle.getParcelable(ArticleInfoFragment.BUNDLE_KEY_INFO);
            binding.setVariable(BR.articleItem, article);
            setArticle(article);
            AppLog.i(" aaaaa " + article);
        }
    }


    public Article getArticle() {
        return article;
    }

    @Bindable
    public void setArticle(Article article) {
        this.article = article;
        notifyPropertyChanged(BR.viewModel);
    }
}

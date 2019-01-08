package com.example.vahe.newsfeed.view.info;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.vahe.newsfeed.BR;
import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.utils.AppLog;
import com.example.vahe.newsfeed.view.BaseFragment;
import com.example.vahe.newsfeed.view.BaseVM;

public class ArticleInfoFragment extends BaseFragment {

    public static final String BUNDLE_ARTICLE_ID_KEY_INFO = "BUNDLE_ARTICLE_ID_KEY_INFO";
    private ArticleVM viewModel;
    private Bundle bundle;
    private Article article;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected BaseVM onCreateViewModel() {
        viewModel = ViewModelProviders.of(this).get(ArticleVM.class);
        return viewModel;
    }

    @Override
    protected BaseVM onBindViewModel(ViewDataBinding binding) {
        return null;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bundle = getArguments();
        String articleId = "";
        if (bundle != null && bundle.containsKey(ArticleInfoFragment.BUNDLE_ARTICLE_ID_KEY_INFO)) {
            articleId = bundle.getString(ArticleInfoFragment.BUNDLE_ARTICLE_ID_KEY_INFO);

        }
        viewModel.init(articleId, "thumbnail,trailText,headline", "5c732610-0d3a-43fa-828e-12ae6b8ebd85");
        viewModel.getArticleLiveData().observe(this, article -> {
            AppLog.i(" article = " + article);
        });


    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.article_info_layout;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

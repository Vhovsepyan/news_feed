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
import com.example.vahe.newsfeed.utils.AppLog;
import com.example.vahe.newsfeed.view.BaseFragment;
import com.example.vahe.newsfeed.view.BaseVM;

public class ArticleInfoFragment extends BaseFragment {

    public static final String BUNDLE_ARTICLE_ID_KEY_INFO = "BUNDLE_ARTICLE_ID_KEY_INFO";
    private ArticleVM viewModel;
    private Bundle bundle;
    private Menu menu;

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
        viewModel.init(articleId);
        viewModel.getArticleFromApiLiveData().observe(this, article -> {
            AppLog.i(" article = " + article);
            viewModel.setArticle(article);
        });

        viewModel.getArticleFromDBLiveData().observe(this, article -> {
            if (article != null && article.isPinned()) {
                viewModel.setArticle(article);
                MenuItem menuItem = menu.findItem(R.id.action_pin);
                menuItem.setTitle(getString(R.string.pinned_text));
            }
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
        inflater.inflate(R.menu.article_info_menu, menu);
        this.menu = menu;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        viewModel.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
}

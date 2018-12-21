package com.example.vahe.newsfeed.screens.info;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.vahe.newsfeed.BR;
import com.example.vahe.newsfeed.NewsFeedApp;
import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.screens.BaseFragment;
import com.example.vahe.newsfeed.screens.BaseVM;

public class ArticleInfoFragment extends BaseFragment {
    public static String BUNDLE_ARTICLE_ID_KEY_INFO = "BUNDLE_ARTICLE_ID_KEY_INFO";
    private ArticleVM viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected BaseVM onCreateViewModel() {
        viewModel = new ArticleVM(getAppContext());
        NewsFeedApp app = (NewsFeedApp) getAppContext();
        app.appComponent().inject(viewModel);
        return viewModel;
    }

    @Override
    protected BaseVM onBindViewModel(ViewDataBinding binding) {
        //ToDo implement functional if needed
        return null;
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
        viewModel.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        viewModel.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        viewModel.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
}

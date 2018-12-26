package com.example.vahe.newsfeed.view.info;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.vahe.newsfeed.BR;
import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.view.BaseFragment;
import com.example.vahe.newsfeed.view.BaseVM;

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
        return null;
    }

    @Override
    protected BaseVM onBindViewModel(ViewDataBinding binding) {
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

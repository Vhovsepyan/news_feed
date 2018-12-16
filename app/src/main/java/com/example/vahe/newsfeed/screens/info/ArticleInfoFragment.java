package com.example.vahe.newsfeed.screens.info;

import android.databinding.ViewDataBinding;

import com.example.vahe.newsfeed.BR;
import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.screens.BaseFragment;
import com.example.vahe.newsfeed.screens.BaseVM;

public class ArticleInfoFragment extends BaseFragment {
    public static String BUNDLE_KEY_INFO = "BUNDLE_KEY_INFO";

    @Override
    protected BaseVM onCreateViewModel(ViewDataBinding binding) {
        return new ArticleVM(getNavController(), getAppContext());
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.article_info_layout;
    }
}

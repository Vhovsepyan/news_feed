package com.example.vahe.newsfeed.screens.home;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.BR;
import com.example.vahe.newsfeed.screens.BaseFragment;
import com.example.vahe.newsfeed.screens.BaseVM;

public class HomeFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BaseVM onCreateViewModel(ViewDataBinding binding) {
        return new PageInfoVM();
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment;
    }
}

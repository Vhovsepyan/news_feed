package com.example.vahe.newsfeed.screens.home;

import android.content.Context;
import android.databinding.ViewDataBinding;

import com.example.vahe.newsfeed.BR;
import com.example.vahe.newsfeed.NewsFeedApp;
import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.screens.BaseFragment;
import com.example.vahe.newsfeed.screens.BaseVM;

public class HomeFragment extends BaseFragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected BaseVM onCreateViewModel(ViewDataBinding binding) {
        PageInfoVM viewModel = new PageInfoVM(getNavController(), getAppContext());
        NewsFeedApp app = (NewsFeedApp) getAppContext();
        app.appComponent().inject(viewModel);
        viewModel.init();
        return viewModel;
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

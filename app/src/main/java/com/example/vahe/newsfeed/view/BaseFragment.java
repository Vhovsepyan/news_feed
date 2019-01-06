package com.example.vahe.newsfeed.view;

import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vahe.newsfeed.NewsFeedApp;

import androidx.navigation.NavController;

public abstract class BaseFragment<B extends ViewDataBinding> extends Fragment {
    private B binding;
    private AndroidViewModel viewModel;
    private NavController navController;
    private NewsFeedApp appContext;
    private Bundle bundle;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.appContext = (NewsFeedApp) context.getApplicationContext();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = onCreateViewModel();
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        bundle = getArguments();
//        onBindViewModel(binding);
        // this is for config changes
        if (navController == null){
            ActivityView activityView = (ActivityView) appContext;
            navController = activityView.getNavController();
        }
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setVariable(getVariable(), viewModel);
        binding.executePendingBindings();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected NewsFeedApp getApplication() {
        return appContext;
    }

    protected NavController getNavController() {
        return navController;
    }

    protected abstract AndroidViewModel onCreateViewModel();

    protected abstract AndroidViewModel onBindViewModel(B binding);

    public abstract int getVariable();

    public abstract int getLayoutId();
}

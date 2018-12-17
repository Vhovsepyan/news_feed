package com.example.vahe.newsfeed.screens;

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

import androidx.navigation.NavController;

public abstract class BaseFragment<B extends ViewDataBinding> extends Fragment {
    private B binding;
    private BaseVM viewModel;
    private NavController navController;
    private Context context;
    private Bundle bundle;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        bundle = getArguments();
        ActivityView activityView = (ActivityView) context;
        navController = activityView.getNavController();
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = onCreateViewModel(binding);
        viewModel.onViewCreated(view, bundle, binding);
        binding.setVariable(getVariable(), viewModel);
        binding.executePendingBindings();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    public void onPause() {
        viewModel.onPause();
        super.onPause();
    }

    @Override
    public void onStop() {
        viewModel.onStop();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        viewModel.onDestroyView();
        super.onDestroyView();
    }

    protected Context getAppContext() {
        return context.getApplicationContext();
    }

    protected NavController getNavController() {
        return navController;
    }

    protected abstract BaseVM onCreateViewModel(B binding);

    public abstract int getVariable();

    public abstract int getLayoutId();
}

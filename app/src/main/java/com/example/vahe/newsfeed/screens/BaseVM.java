package com.example.vahe.newsfeed.screens;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.vahe.newsfeed.providers.ExecutorService;
import com.example.vahe.newsfeed.providers.ExecutorType;

import java.util.concurrent.Executor;

import androidx.navigation.NavController;

public abstract class BaseVM<T extends BaseFragment> extends BaseObservable {
    private NavController navController;
    private Context appContext;

    public BaseVM(NavController navController, Context appContext) {
        this.navController = navController;
        this.appContext = appContext;
    }

    protected abstract void init();

    protected void onCreateView() {
    }

    protected void onViewCreated(View view, Bundle bundle, ViewDataBinding binding) {
    }

    protected void onStart() {
    }

    protected void onResume() {
    }

    ;

    protected void onPause() {
    }

    ;

    protected void onStop() {
    }

    protected void onDestroyView() {
    }

    protected void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    }

    public void onPrepareOptionsMenu(Menu menu) {
    }

    protected void onOptionsItemSelected(MenuItem item) {
    }

    public NavController getNavController() {
        return navController;
    }

    public Context getAppContext() {
        return appContext;
    }

    protected String getString(int resId) {
        return getAppContext().getString(resId);
    }

    protected Executor getExecutor(@ExecutorType int type) {
        return ExecutorService.getExecutor(type);
    }
}

package com.example.vahe.newsfeed.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.example.vahe.newsfeed.executors.ExecutorService;
import com.example.vahe.newsfeed.executors.ExecutorType;
import com.example.vahe.newsfeed.utils.SharedPrefs;

import java.util.concurrent.Executor;

public abstract class BaseVM extends AndroidViewModel {


    public BaseVM(@NonNull Application application) {
        super(application);
    }

    protected Executor getExecutor(@ExecutorType int type) {
        return ExecutorService.getExecutor(type);
    }

    protected SharedPrefs getPreferences() {
        return SharedPrefs.getInstance();
    }

    protected void onOptionsItemSelected(MenuItem item) {
    }

    protected String getString(int resId) {
        return getApplication().getString(resId);
    }
}

package com.example.vahe.newsfeed.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.vahe.newsfeed.executors.ExecutorService;
import com.example.vahe.newsfeed.executors.ExecutorType;
import com.example.vahe.newsfeed.utils.SharedPrefs;

import java.util.concurrent.Executor;

import androidx.navigation.NavController;

public abstract class BaseVM extends AndroidViewModel {

    private NavController navController;

    public BaseVM(@NonNull Application application) {
        super(application);
    }

    public void setNavController(NavController navController) {
        this.navController = navController;
    }

    protected Executor getExecutor(@ExecutorType int type) {
        return ExecutorService.getExecutor(type);
    }

    protected SharedPrefs getPreferences() {
        return SharedPrefs.getInstance();
    }
}

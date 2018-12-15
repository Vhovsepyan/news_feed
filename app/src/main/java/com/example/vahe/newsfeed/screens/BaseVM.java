package com.example.vahe.newsfeed.screens;

import android.databinding.BaseObservable;

import com.example.vahe.newsfeed.providers.ExecutorService;
import com.example.vahe.newsfeed.providers.ExecutorType;

import java.util.concurrent.Executor;

public class BaseVM<T extends BaseFragment> extends BaseObservable {

    protected void onCreateView(){}

    protected void onViewCreated(){}

    protected void onStart(){}

    protected void onResume(){};

    protected void onPause(){};

    protected void onStop(){}

    protected void onDestroyView(){}




    protected Executor getExecutor(@ExecutorType int type){
        return ExecutorService.getExecutor(type);
    }
}

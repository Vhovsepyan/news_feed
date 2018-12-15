package com.example.vahe.newsfeed1.screens;

import android.databinding.BaseObservable;

import com.example.vahe.newsfeed1.providers.ExecutorService;
import com.example.vahe.newsfeed1.providers.ExecutorType;

import java.util.concurrent.Executor;

public class BaseVM<T extends BaseFragment> extends BaseObservable {

    protected Executor getExecutor(@ExecutorType int type){
        return ExecutorService.getExecutor(type);
    }
}

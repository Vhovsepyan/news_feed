package com.example.vahe.newsfeed.providers;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class ExecutorProviderImpl implements ExecutorProvider {
    private final ExecutorService executorService;
    private final Executor mainExecutor;

    ExecutorProviderImpl() {
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        this.mainExecutor = handler::post;
    }

    @Override
    public Executor getExecutor(@ExecutorType int type) {
        switch (type) {
            case ExecutorType.MAIN:
                return mainExecutor;
            case ExecutorType.BACKGROUND:
                return executorService;//TODO
            case ExecutorType.MAIN_BACKGROUND:
                return executorService;//TODO
            default:
                return null;
        }
    }
}

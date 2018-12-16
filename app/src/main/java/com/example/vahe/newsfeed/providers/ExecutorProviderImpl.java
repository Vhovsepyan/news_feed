package com.example.vahe.newsfeed.providers;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class ExecutorProviderImpl implements ExecutorProvider {
    private final ExecutorService background;
    private final ExecutorService dbCommunication;
    private final ExecutorService serverCommunication;
    private final Executor mainExecutor;

    ExecutorProviderImpl() {
        this.background = Executors.newSingleThreadScheduledExecutor();
        this.dbCommunication = Executors.newSingleThreadScheduledExecutor();
        this.serverCommunication = Executors.newSingleThreadScheduledExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        this.mainExecutor = handler::post;
    }

    @Override
    public Executor getExecutor(@ExecutorType int type) {
        switch (type) {
            case ExecutorType.MAIN:
                return mainExecutor;
            case ExecutorType.BACKGROUND:
                return background;
            case ExecutorType.DB_COMMUNICATION:
                return dbCommunication;
            case ExecutorType.SERVER_COMMUNICATION:
                return serverCommunication;
            default:
                return null;
        }
    }
}

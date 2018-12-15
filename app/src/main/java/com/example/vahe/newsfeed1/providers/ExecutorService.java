package com.example.vahe.newsfeed1.providers;

import java.util.concurrent.Executor;

public class ExecutorService {

    private static ExecutorProvider executorProvider = new ExecutorProviderImpl();

    public static Executor getExecutor(@ExecutorType int type){
        return executorProvider.getExecutor(type);
    }
}

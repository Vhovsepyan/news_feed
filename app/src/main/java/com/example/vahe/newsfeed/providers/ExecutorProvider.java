package com.example.vahe.newsfeed.providers;

import java.util.concurrent.Executor;

interface ExecutorProvider {
    Executor getExecutor(@ExecutorType int type);
}

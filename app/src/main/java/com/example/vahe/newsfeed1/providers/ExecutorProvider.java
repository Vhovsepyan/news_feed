package com.example.vahe.newsfeed1.providers;

import java.util.concurrent.Executor;

interface ExecutorProvider {
    Executor getExecutor(@ExecutorType int type);
}

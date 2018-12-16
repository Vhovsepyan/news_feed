package com.example.vahe.newsfeed.executors;

import java.util.concurrent.Executor;

interface ExecutorProvider {
    Executor getExecutor(@ExecutorType int type);
}

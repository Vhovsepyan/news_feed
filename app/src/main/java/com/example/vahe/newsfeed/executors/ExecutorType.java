package com.example.vahe.newsfeed.executors;

import android.support.annotation.IntDef;

@IntDef({
        ExecutorType.MAIN,
        ExecutorType.DB_COMMUNICATION,
        ExecutorType.BACKGROUND,
        ExecutorType.SERVER_COMMUNICATION,
})
public @interface ExecutorType {
    int MAIN = -1;
    int DB_COMMUNICATION = 0;
    int BACKGROUND = 1;
    int SERVER_COMMUNICATION = 2;
}

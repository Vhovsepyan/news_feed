package com.example.vahe.newsfeed.providers;

import android.support.annotation.IntDef;

@IntDef({
        ExecutorType.MAIN,
        ExecutorType.MAIN_BACKGROUND,
        ExecutorType.BACKGROUND,
})
public @interface ExecutorType {
    int MAIN = -1;
    int MAIN_BACKGROUND = 0;
    int BACKGROUND = 1;
}

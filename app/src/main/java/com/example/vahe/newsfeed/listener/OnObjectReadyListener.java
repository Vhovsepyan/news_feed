package com.example.vahe.newsfeed.listener;

import com.example.vahe.newsfeed.model.BaseObject;

public interface OnObjectReadyListener<T extends BaseObject> {
    void onReady(T obj);
}

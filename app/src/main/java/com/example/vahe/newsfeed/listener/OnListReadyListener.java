package com.example.vahe.newsfeed.listener;

import com.example.vahe.newsfeed.model.BaseObject;

import java.util.List;

public interface OnListReadyListener<T extends BaseObject> {

    void onReady(List<T> list);

}

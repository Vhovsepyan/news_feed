package com.example.vahe.newsfeed.screens;

import com.example.vahe.newsfeed.model.BaseObject;

public interface BaseClickListener<T extends BaseObject> {

    void onItemClickListener(T obj);
}

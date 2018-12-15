package com.example.vahe.newsfeed1.model;

public interface BaseObject {
    int OBJECT_TYPE_PAGE_INFO = 0;
    int OBJECT_TYPE_NEWS_ITEM = 1;

    default int getObjectType() {
        return 0;
    }
}
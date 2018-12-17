package com.example.vahe.newsfeed.model;

public interface BaseObject {
    int OBJECT_TYPE_ARTICLE = 0;
    int OBJECT_TYPE_PAGE_INFO = 1;

    default int getObjectType() {
        return 0;
    }
}
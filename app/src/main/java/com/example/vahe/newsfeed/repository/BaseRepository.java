package com.example.vahe.newsfeed.repository;

import com.example.vahe.newsfeed.model.BaseObject;

public interface BaseRepository<T extends BaseObject> {

    void insert(T items);

    void update(T items);

    void delete(T items);
}

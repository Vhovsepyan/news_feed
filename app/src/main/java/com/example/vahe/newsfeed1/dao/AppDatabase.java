package com.example.vahe.newsfeed1.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.vahe.newsfeed1.model.NewsItem;

@Database(entities = {NewsItem.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {
/*
    public abstract DaoGroup getGroupDao();

    public abstract DaoUser getUserDao();

    public abstract DaoGroupUserRelation getGroupUserRelationDao();

    public abstract DaoMessage getMessageDao();*/
}

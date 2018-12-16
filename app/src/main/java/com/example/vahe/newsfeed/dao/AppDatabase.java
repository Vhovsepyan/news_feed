package com.example.vahe.newsfeed.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.vahe.newsfeed.model.Article;

@Database(entities = {Article.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {
/*
    public abstract DaoGroup getGroupDao();

    public abstract DaoUser getUserDao();

    public abstract DaoGroupUserRelation getGroupUserRelationDao();

    public abstract DaoMessage getMessageDao();*/
}

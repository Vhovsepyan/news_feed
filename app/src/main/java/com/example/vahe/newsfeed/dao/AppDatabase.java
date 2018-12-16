package com.example.vahe.newsfeed.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.vahe.newsfeed.model.entity.ArticleTable;

@Database(entities = {ArticleTable.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ArticleDao getArticleDao();

}

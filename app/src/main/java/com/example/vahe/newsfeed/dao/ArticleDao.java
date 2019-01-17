package com.example.vahe.newsfeed.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.vahe.newsfeed.model.entity.ArticleTable;

import java.util.List;

@Dao
public interface ArticleDao extends BaseDao<ArticleTable> {

    @Query("SELECT * FROM article_table WHERE `id` = :articleId")
    ArticleTable getArticleById(String articleId);

    @Query("SELECT * FROM article_table")
    LiveData<List<ArticleTable>> getPinnedArticles();

}

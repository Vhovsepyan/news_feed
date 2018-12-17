package com.example.vahe.newsfeed.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.vahe.newsfeed.model.Article;

@Entity(tableName = "article_table")
public class ArticleTable {

    @PrimaryKey
    @NonNull
    private String id;

    private String type;

    private String sectionId;

    private String sectionName;

    private String webPublicationDate;

    private String webTitle;

    private String webUrl;

    private String thumbnail;

    private String trailText;

    private String body;

    private Boolean isPinned;

    public ArticleTable(@NonNull String id) {
        this.id = id;
    }


    public ArticleTable(Article article) {
        this.id = article.getId();
        this.type = article.getType();
        this.sectionId = article.getSectionId();
        this.sectionName = article.getSectionName();
        this.webPublicationDate = article.getWebPublicationDate();
        this.webTitle = article.getWebTitle();
        this.webUrl = article.getWebUrl();
        this.thumbnail = article.getThumbnail();
        this.trailText = article.getTrailText();
        this.body = article.getBody();
        this.isPinned = article.isPinned();
    }


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTrailText() {
        return trailText;
    }

    public void setTrailText(String trailText) {
        this.trailText = trailText;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean isPinned() {
        return isPinned;
    }

    public void setPinned(Boolean pinned) {
        isPinned = pinned;
    }
}

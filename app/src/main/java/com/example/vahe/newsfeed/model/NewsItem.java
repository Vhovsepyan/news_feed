package com.example.vahe.newsfeed.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.vahe.newsfeed.model.requestModel.NewsItemRequestModel;

@Entity
public class NewsItem implements BaseObject {

    @PrimaryKey
    @NonNull
    private String id;

    private String type;

    private String sectionId;

    private String sectionName;

    private String webPublicationDate;

    private String webTitle;

    private String webUrl;

    private String apiUrl;

    private Boolean isHosted;

    private String pillarId;

    private String pillarName;

    public NewsItem() {
    }

    public NewsItem(NewsItemRequestModel requestModel) {
        this.id = requestModel.getId();
        this.type = requestModel.getType();
        this.sectionId = requestModel.getSectionId();
        this.sectionName = requestModel.getSectionName();
        this.webPublicationDate = requestModel.getWebPublicationDate();
        this.webTitle = requestModel.getWebTitle();
        this.webUrl = requestModel.getWebUrl();
        this.apiUrl = requestModel.getApiUrl();
        this.isHosted = requestModel.getIsHosted();
        this.pillarId = requestModel.getPillarId();
        this.pillarName = requestModel.getPillarName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public Boolean getHosted() {
        return isHosted;
    }

    public void setHosted(Boolean hosted) {
        isHosted = hosted;
    }

    public String getPillarId() {
        return pillarId;
    }

    public void setPillarId(String pillarId) {
        this.pillarId = pillarId;
    }

    public String getPillarName() {
        return pillarName;
    }

    public void setPillarName(String pillarName) {
        this.pillarName = pillarName;
    }
}

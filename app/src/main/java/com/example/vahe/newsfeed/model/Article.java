package com.example.vahe.newsfeed.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.example.vahe.newsfeed.model.requestModel.ArticleRequestModel;

@Entity
public class Article implements BaseObject, Parcelable {

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

    public Article() {
    }

    public Article(ArticleRequestModel requestModel) {
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

    protected Article(Parcel in) {
        id = in.readString();
        type = in.readString();
        sectionId = in.readString();
        sectionName = in.readString();
        webPublicationDate = in.readString();
        webTitle = in.readString();
        webUrl = in.readString();
        apiUrl = in.readString();
        byte tmpIsHosted = in.readByte();
        isHosted = tmpIsHosted == 0 ? null : tmpIsHosted == 1;
        pillarId = in.readString();
        pillarName = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(type);
        parcel.writeString(sectionId);
        parcel.writeString(sectionName);
        parcel.writeString(webPublicationDate);
        parcel.writeString(webTitle);
        parcel.writeString(webUrl);
        parcel.writeString(apiUrl);
        parcel.writeByte((byte) (isHosted == null ? 0 : isHosted ? 1 : 2));
        parcel.writeString(pillarId);
        parcel.writeString(pillarName);
    }
}

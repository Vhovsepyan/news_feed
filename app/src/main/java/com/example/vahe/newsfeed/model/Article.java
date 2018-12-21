package com.example.vahe.newsfeed.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.vahe.newsfeed.model.entity.ArticleTable;
import com.example.vahe.newsfeed.model.request.ArticleRequestModel;

import java.util.Random;

public class Article implements BaseObject, Parcelable, Cloneable {

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

    private boolean isPinned;

    private int thumbnailWidth;

    private int thumbnailHeight;

    public Article() {
    }

    public Article(ArticleTable articleTable) {
        this.id = articleTable.getId();
        this.type = articleTable.getType();
        this.sectionId = articleTable.getSectionId();
        this.sectionName = articleTable.getSectionName();
        this.webPublicationDate = articleTable.getWebPublicationDate();
        this.webTitle = articleTable.getWebTitle();
        this.webUrl = articleTable.getWebUrl();
        this.thumbnail = articleTable.getThumbnail();
        this.trailText = articleTable.getTrailText();
        this.body = articleTable.getBody();
        this.isPinned = articleTable.isPinned();
        this.thumbnailHeight = getRandomHeight();
        this.thumbnailWidth = getRandomWidth();
    }

    public Article(ArticleRequestModel requestModel) {
        this.id = requestModel.getId();
        this.type = requestModel.getType();
        this.sectionId = requestModel.getSectionId();
        this.sectionName = requestModel.getSectionName();
        this.webPublicationDate = requestModel.getWebPublicationDate();
        this.webTitle = requestModel.getWebTitle();
        this.webUrl = requestModel.getWebUrl();
        this.thumbnail = requestModel.getFields().getThumbnail();
        this.trailText = requestModel.getFields().getTrailText();
        this.body = requestModel.getFields().getBody();
        this.thumbnailHeight = getRandomHeight();
        this.thumbnailWidth = getRandomWidth();
    }

    protected Article(Parcel in) {
        id = in.readString();
        type = in.readString();
        sectionId = in.readString();
        sectionName = in.readString();
        webPublicationDate = in.readString();
        webTitle = in.readString();
        webUrl = in.readString();
        thumbnail = in.readString();
        trailText = in.readString();
        body = in.readString();
        isPinned = in.readByte() != 0;
        thumbnailWidth = in.readInt();
        thumbnailHeight = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(type);
        dest.writeString(sectionId);
        dest.writeString(sectionName);
        dest.writeString(webPublicationDate);
        dest.writeString(webTitle);
        dest.writeString(webUrl);
        dest.writeString(thumbnail);
        dest.writeString(trailText);
        dest.writeString(body);
        dest.writeByte((byte) (isPinned ? 1 : 0));
        dest.writeInt(thumbnailWidth);
        dest.writeInt(thumbnailHeight);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    public int getThumbnailWidth() {
        return thumbnailWidth;
    }

    public int getThumbnailHeight() {
        return thumbnailHeight;
    }

    private int getRandomHeight() {
        Random r = new Random();
        int low = 200;
        int high = 400;
        return r.nextInt(high - low) + low;
    }

    private int getRandomWidth() {
        Random r = new Random();
        int low = 400;
        int high = 500;
        return r.nextInt(high - low) + low;
    }

    @Override
    public int getObjectType() {
        return OBJECT_TYPE_ARTICLE;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

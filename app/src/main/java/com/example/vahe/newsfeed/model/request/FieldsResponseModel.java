package com.example.vahe.newsfeed.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FieldsResponseModel {

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @SerializedName("trailText")
    @Expose
    private String trailText;

    @SerializedName("headline")
    @Expose
    private String headline;

    @SerializedName("body")
    @Expose
    private String body;

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

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
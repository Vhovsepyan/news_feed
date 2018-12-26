package com.example.vahe.newsfeed.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseModel {

    @SerializedName("response")
    @Expose
    private PageInfoResponseModel response;

    public PageInfoResponseModel getResponse() {
        return response;
    }

    public void setResponse(PageInfoResponseModel response) {
        this.response = response;
    }

}
package com.example.vahe.newsfeed.model.request;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PageInfoResponseModel {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("userTier")
    @Expose
    private String userTier;

    @SerializedName("total")
    @Expose
    private Integer total;

    @SerializedName("startIndex")
    @Expose
    private Integer startIndex;

    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;

    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;

    @SerializedName("pages")
    @Expose
    private Integer pages;

    @SerializedName("orderBy")
    @Expose
    private String orderBy;

    @SerializedName("results")
    @Expose
    private List<ArticleResponseModel> articleResponseModels = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserTier() {
        return userTier;
    }

    public void setUserTier(String userTier) {
        this.userTier = userTier;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List<ArticleResponseModel> getArticleResponseModels() {
        return articleResponseModels;
    }

    public void setArticleResponseModels(List<ArticleResponseModel> articleResponseModels) {
        this.articleResponseModels = articleResponseModels;
    }

    @NonNull
    @Override
    public String toString() {
        return "PageInfoResponseModel{" +
                "status='" + status + '\'' +
                ", userTier='" + userTier + '\'' +
                ", total=" + total +
                ", startIndex=" + startIndex +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", pages=" + pages +
                ", orderBy='" + orderBy + '\'' +
                ", articleResponseModels=" + articleResponseModels +
                '}';
    }
}
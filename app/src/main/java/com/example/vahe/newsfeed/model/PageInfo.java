package com.example.vahe.newsfeed.model;

import com.example.vahe.newsfeed.model.requestModel.ArticleRequestModel;
import com.example.vahe.newsfeed.model.requestModel.PageInfoRequestModel;

import java.util.ArrayList;
import java.util.List;

public class PageInfo implements BaseObject {

    private String status;

    private String userTier;

    private Integer total;

    private Integer startIndex;

    private Integer pageSize;

    private Integer currentPage;

    private Integer pages;

    private String orderBy;

    private List<Article> articles = null;

    public PageInfo() {
    }

    public PageInfo(PageInfoRequestModel pageInfoRequestModel) {
        this.status = pageInfoRequestModel.getStatus();
        this.userTier = pageInfoRequestModel.getUserTier();
        this.total = pageInfoRequestModel.getTotal();
        this.startIndex = pageInfoRequestModel.getStartIndex();
        this.pageSize = pageInfoRequestModel.getPageSize();
        this.currentPage = pageInfoRequestModel.getCurrentPage();
        this.pages = pageInfoRequestModel.getPages();
        this.orderBy = pageInfoRequestModel.getOrderBy();
        initNews(pageInfoRequestModel.getArticleRequestModels());

    }

    private void initNews(List<ArticleRequestModel> articleRequestModels){
        articles = new ArrayList<>();
        for (int i = 0; i < articleRequestModels.size(); i++) {
            articles.add(new Article(articleRequestModels.get(i)));
        }
    }

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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}

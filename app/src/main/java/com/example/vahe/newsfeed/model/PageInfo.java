package com.example.vahe.newsfeed.model;

import android.support.annotation.IntDef;

import com.example.vahe.newsfeed.model.request.ArticleContentModel;
import com.example.vahe.newsfeed.model.request.PageInfoResponseModel;

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

    private int containerType;

    public PageInfo() {
    }

    public PageInfo(PageInfoResponseModel pageInfoResponseModel) {
        this.status = pageInfoResponseModel.getStatus();
        this.userTier = pageInfoResponseModel.getUserTier();
        this.total = pageInfoResponseModel.getTotal();
        this.startIndex = pageInfoResponseModel.getStartIndex();
        this.pageSize = pageInfoResponseModel.getPageSize();
        this.currentPage = pageInfoResponseModel.getCurrentPage();
        this.pages = pageInfoResponseModel.getPages();
        this.orderBy = pageInfoResponseModel.getOrderBy();
        this.containerType = PageInfoContainerType.BODY;
        initNews(pageInfoResponseModel.getArticleContentModels());

    }

    private void initNews(List<ArticleContentModel> articleContentModels) {
        articles = new ArrayList<>();
        if (articleContentModels != null){
            for (int i = 0; i < articleContentModels.size(); i++) {
                ArticleContentModel requestModel = articleContentModels.get(i);
                Article article = new Article(requestModel);
                articles.add(article);
            }
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

    public int getContainerType() {
        return containerType;
    }

    public void setContainerType(@PageInfoContainerType int containerType) {
        this.containerType = containerType;
    }

    @Override
    public int getObjectType() {
        return OBJECT_TYPE_PAGE_INFO;
    }

    @IntDef({PageInfoContainerType.HEADER, PageInfoContainerType.BODY})
    public @interface PageInfoContainerType{
        int HEADER = 0;
        int BODY = 1;
    }
}

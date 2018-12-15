package com.example.vahe.newsfeed1.model;

import com.example.vahe.newsfeed1.model.requestModel.NewsItemRequestModel;
import com.example.vahe.newsfeed1.model.requestModel.PageInfoRequestModel;

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

    private List<NewsItem> newsItems = null;

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
        initNews(pageInfoRequestModel.getNewsItemRequestModels());

    }

    private void initNews(List<NewsItemRequestModel> newsItemRequestModels){
        newsItems = new ArrayList<>();
        for (int i = 0; i < newsItemRequestModels.size(); i++) {
            newsItems.add(new NewsItem(newsItemRequestModels.get(i)));
        }
    }
}

package com.example.vahe.newsfeed.utils;

import android.text.TextUtils;

public class ArticleUrlBuilder {
    private final static String API_KEY = "api-key";
    private final static String API_VALUE = "5c732610-0d3a-43fa-828e-12ae6b8ebd85";
    private final static String TARGET_URL = "https://content.guardianapis.com/search?";
    private final String fromDate = "&from-date";
    private String toDate = "&to-date";
    private String page = "&page";
    private String pageSize = "&page-size";
    private String useDate = "&use-date";
    private String orderBy = "&order-by";
    private String orderDate = "&order-date";
    private String showFields = "show-fields=thumbnail,trailText,headline,body";

    private String fromDateValue;
    private String toDateValue;
    private int pageValue;
    private int pageSizeValue = 50;
    private String useDateValue;
    private String orderByValue;
    private String orderDateValue;

    private StringBuilder urlBuilder = new StringBuilder();

    public ArticleUrlBuilder() {
        urlBuilder.append(TARGET_URL)
                .append(API_KEY)
                .append("=")
                .append(API_VALUE)
                .append("&")
                .append(showFields);
    }

    public ArticleUrlBuilder addFromDate(String fromDate) {
        this.fromDateValue = fromDate;
        return this;
    }

    public ArticleUrlBuilder addToDate(String toDate) {
        this.toDateValue = toDate;
        return this;
    }

    public ArticleUrlBuilder addUseDate(String useDate) {
        this.useDateValue = useDate;
        return this;
    }

    public ArticleUrlBuilder addOrderBy(String orderBy) {
        this.orderByValue = orderBy;
        return this;
    }

    public ArticleUrlBuilder addOrderDate(String orderDate) {
        this.orderDateValue = orderDate;
        return this;

    }

    public ArticleUrlBuilder addPage(int page) {
        this.pageValue = page;
        return this;
    }

    public ArticleUrlBuilder addPageSize(int pageSize) {
        this.pageSizeValue = pageSize;
        return this;
    }

    public String build() {
        if (!TextUtils.isEmpty(toDateValue)) {
            urlBuilder.append(this.toDate)
                    .append("=")
                    .append(toDateValue);
        }
        if (!TextUtils.isEmpty(fromDateValue)) {
            urlBuilder.append(this.fromDate)
                    .append("=")
                    .append(fromDateValue);
        }
        if (!TextUtils.isEmpty(useDateValue)) {
            urlBuilder.append(this.useDate)
                    .append("=")
                    .append(useDateValue);
        }

        if (!TextUtils.isEmpty(orderByValue)) {
            urlBuilder.append(this.orderBy)
                    .append("=")
                    .append(orderByValue);
        }
        if (!TextUtils.isEmpty(orderDateValue)) {
            urlBuilder.append(this.orderDate)
                    .append("=")
                    .append(orderDateValue);
        }

        if (pageValue > 0) {
            urlBuilder.append(page)
                    .append("=")
                    .append(pageValue);
        }

        if (pageSizeValue > 0) {
            urlBuilder.append(pageSize)
                    .append("=")
                    .append(pageSizeValue);
        }

        return urlBuilder.toString();
    }
}

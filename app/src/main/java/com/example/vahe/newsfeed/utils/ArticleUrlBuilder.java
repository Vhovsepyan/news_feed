package com.example.vahe.newsfeed.utils;

import android.text.TextUtils;

public class ArticleUrlBuilder {
    private final static String API_KEY = "api-key";
    private final static String API_VALUE = "5c732610-0d3a-43fa-828e-12ae6b8ebd85";
    private final static String TARGET_URL = "http://content.guardianapis.com/search?";
    private final String fromDate = "&from-date";
    private String toDate = "&to-date";
    private String pageSize = "&page-size";
    private String showFields = "show-fields=thumbnail,trailText,headline,body";

    private String fromDateValue;
    private String toDateValue;
    private int pageSizeValue = 20;

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
        if (pageSizeValue >= 0) {
            urlBuilder.append(pageSize)
                    .append("=")
                    .append(pageSizeValue);
        }

        return urlBuilder.toString();
    }
}

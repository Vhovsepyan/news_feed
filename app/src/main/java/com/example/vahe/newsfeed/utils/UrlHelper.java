package com.example.vahe.newsfeed.utils;

public class UrlHelper {
    public static String getArticleApiUrl(String id) {
        return UrlConstants.BASE_URL + "/" + id;
    }
}

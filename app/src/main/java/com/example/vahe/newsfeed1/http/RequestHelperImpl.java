package com.example.vahe.newsfeed1.http;


import com.example.vahe.newsfeed1.utils.Constants;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestHelperImpl implements RequestHelper {

    private OkHttpClient client = new OkHttpClient();

    @Override
    public String getNews(String url) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader(Constants.API_KEY, Constants.API_VALUE)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.body() != null) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}

package com.example.vahe.newsfeed.repository;

import com.example.vahe.newsfeed.http.RequestHelper;
import com.example.vahe.newsfeed.http.RequestHelperImpl;
import com.example.vahe.newsfeed.model.PageInfo;
import com.example.vahe.newsfeed.model.requestModel.PageInfoRequestModel;
import com.example.vahe.newsfeed.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

public class NewsRepositoryImpl implements NewsRepository {
    private RequestHelper requestHelper;

    public NewsRepositoryImpl() {
        this.requestHelper = new RequestHelperImpl();
    }

    @Override
    public PageInfo getPageInfoFromApi() {
        String data = requestHelper.getNews(Constants.SEARCH_URL);
        PageInfo pageInfo = null;
        try {

            JSONObject jsonObject = new JSONObject(data);
            Gson gson = new GsonBuilder().create();
            Type collectionType = new TypeToken<PageInfoRequestModel>() {}.getType();
            PageInfoRequestModel info = gson.fromJson(jsonObject.getJSONObject(Constants.RESPONSE_KEY).toString(), collectionType);
            pageInfo = new PageInfo(info);

        } catch (JSONException ex){
            ex.printStackTrace();
        }
        return pageInfo;
    }
}

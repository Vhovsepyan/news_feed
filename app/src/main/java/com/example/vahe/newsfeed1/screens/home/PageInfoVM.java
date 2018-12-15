package com.example.vahe.newsfeed1.screens.home;

import android.databinding.ObservableField;

import com.example.vahe.newsfeed1.http.RequestHelper;
import com.example.vahe.newsfeed1.http.RequestHelperImpl;
import com.example.vahe.newsfeed1.model.requestModel.NewsItemRequestModel;
import com.example.vahe.newsfeed1.model.requestModel.PageInfoRequestModel;
import com.example.vahe.newsfeed1.providers.ExecutorType;
import com.example.vahe.newsfeed1.screens.BaseVM;
import com.example.vahe.newsfeed1.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class PageInfoVM extends BaseVM {
    public ObservableField<PageInfoRequestModel> pageInfo = new ObservableField<>();
    private List<NewsItemRequestModel> newsItemRequestModels;
    private RequestHelper requestHelper;

    public PageInfoVM() {
        this.requestHelper = new RequestHelperImpl();
        getNews();
    }

    private void getNews(){
        getExecutor(ExecutorType.BACKGROUND).execute(()->{
            String data = requestHelper.getNews(Constants.SEARCH_URL);
            try {
                JSONObject jsonObject = new JSONObject(data);
                Gson gson = new GsonBuilder().create();
                Type collectionType = new TypeToken<PageInfoRequestModel>() {}.getType();
                PageInfoRequestModel info = gson.fromJson(jsonObject.getJSONObject(Constants.RESPONSE_KEY).toString(), collectionType);
                pageInfo.set(info);
                newsItemRequestModels = info.getNewsItemRequestModels();
            } catch (JSONException ex){
                ex.printStackTrace();
            }
        });
    }
}

package com.example.vahe.newsfeed.screens.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.BaseObject;
import com.example.vahe.newsfeed.listener.BaseClickListener;
import com.example.vahe.newsfeed.screens.BaseRecyclerViewHolder;

public class ViewHolderProvider<T extends BaseObject> {

    public BaseRecyclerViewHolder<T> provideViewHolder(LayoutInflater inflater,
                                                       ViewGroup viewGroup,
                                                       BaseClickListener baseClickListener,
                                                       T obj){
        switch (obj.getObjectType()){
            case BaseObject.OBJECT_TYPE_ARTICLE:{
                return provideArticleViewHolder(inflater, viewGroup, baseClickListener, obj);
            }
            default:{
                return null;
            }
        }

    }

    @SuppressWarnings("unchecked")
    private BaseRecyclerViewHolder<T> provideArticleViewHolder(LayoutInflater inflater,
                                                                     ViewGroup viewGroup,
                                                                     BaseClickListener baseClickListener,
                                                                     T obj){
        Article article = (Article)obj;
        if (article.isPinned()) {
            return new ArticleHorizontalViewHolder(
                    inflater.inflate(R.layout.article_item_horizontal_layout, viewGroup, false),
                    baseClickListener);
        } else {
            return new ArticleVerticalViewHolder(
                    inflater.inflate(R.layout.article_item_vertical_layout, viewGroup, false),
                    baseClickListener);
        }
    }
}

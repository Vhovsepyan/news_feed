package com.example.vahe.newsfeed.view.viewholders;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.listener.BaseClickListener;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.BaseObject;
import com.example.vahe.newsfeed.model.PageInfo;
import com.example.vahe.newsfeed.view.home.ArticleListViewModel;

public class ViewHolderProvider<T extends BaseObject> {

    private int viewMode;

    public void updateViewMode(int viewMode) {
        this.viewMode = viewMode;
    }

    public BaseRecyclerViewHolder<T> provideViewHolder(LayoutInflater inflater,
                                                       ViewGroup viewGroup,
                                                       BaseClickListener baseClickListener,
                                                       T obj) {
        switch (obj.getObjectType()) {
            case BaseObject.OBJECT_TYPE_ARTICLE: {
                return provideArticleViewHolder(inflater, viewGroup, baseClickListener, obj);
            }
            case BaseObject.OBJECT_TYPE_PAGE_INFO:{
                return providePageInfoViewHolder(inflater, viewGroup, baseClickListener, obj);
            }
            default: {
                return null;
            }
        }

    }

    @SuppressWarnings("unchecked")
    private BaseRecyclerViewHolder<T> providePageInfoViewHolder(LayoutInflater inflater,
                                                               ViewGroup viewGroup,
                                                               BaseClickListener baseClickListener,
                                                               T obj) {
        PageInfo pageInfo = (PageInfo) obj;
        switch (pageInfo.getContainerType()) {
            case PageInfo.PageInfoContainerType.HEADER: {
                return new ArticleVerticalViewHolder(
                        inflater.inflate(R.layout.article_item_vertical_layout, viewGroup, false),
                        baseClickListener);
            }

            default:
            case PageInfo.PageInfoContainerType.BODY: {
                return new ArticelStaggeredViewHolder(
                        inflater.inflate(R.layout.article_item_pinterest_layout, viewGroup, false),
                        baseClickListener);
            }

        }
    }

    @SuppressWarnings("unchecked")
    private BaseRecyclerViewHolder<T> provideArticleViewHolder(LayoutInflater inflater,
                                                               ViewGroup viewGroup,
                                                               BaseClickListener baseClickListener,
                                                               T obj) {
        Article article = (Article) obj;
        if (article.isPinned()) {
            return new ArticleHorizontalViewHolder(
                    inflater.inflate(R.layout.article_item_header_layout, viewGroup, false),
                    baseClickListener);
        } else {
            switch (viewMode) {
                default:
                case ArticleListViewModel.RecyclerViewSwitchModeDef.VERTICAL: {
                    return new ArticleVerticalViewHolder(
                            inflater.inflate(R.layout.article_item_vertical_layout, viewGroup, false),
                            baseClickListener);
                }
                case ArticleListViewModel.RecyclerViewSwitchModeDef.PINTEREST: {
                    return new ArticelStaggeredViewHolder(
                            inflater.inflate(R.layout.article_item_pinterest_layout, viewGroup, false),
                            baseClickListener);
                }

            }

        }
    }
}

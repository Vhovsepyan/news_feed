package com.example.vahe.newsfeed.view.adapter;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.listener.BaseClickListener;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.NetworkState;
import com.example.vahe.newsfeed.view.viewholders.ArticleVerticalViewHolder;
import com.example.vahe.newsfeed.view.viewholders.BaseRecyclerViewHolder;
import com.example.vahe.newsfeed.view.viewholders.NetworkStateItemViewHolder;

public class ArticleAdapter extends PagedListAdapter<Article, BaseRecyclerViewHolder> {


    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_ITEM = 1;

    private NetworkState networkState;
    private BaseClickListener baseClickListener;

    public ArticleAdapter(BaseClickListener baseClickListener) {
        super(Article.DIFF_CALLBACK);
        this.baseClickListener = baseClickListener;
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        if(viewType == TYPE_PROGRESS) {
            View view = layoutInflater.inflate(R.layout.item_network_state, viewGroup, false);
            NetworkStateItemViewHolder viewHolder = new NetworkStateItemViewHolder(view);
            return viewHolder;

        } else {
            View view = layoutInflater.inflate(R.layout.article_item_vertical_layout, viewGroup, false);
            ArticleVerticalViewHolder viewHolder = new ArticleVerticalViewHolder(view , baseClickListener);
            return viewHolder;
        }
    }
    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder baseRecyclerViewHolder, int i) {
        if (baseRecyclerViewHolder instanceof NetworkStateItemViewHolder){
            baseRecyclerViewHolder.bind(networkState);
        } else {
            baseRecyclerViewHolder.bind(getItem(i));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_ITEM;
        }
    }

    private boolean hasExtraRow() {
        if (networkState != null && networkState != NetworkState.LOADED) {
            return true;
        } else {
            return false;
        }
    }

    public void setNetworkState(NetworkState newNetworkState) {
        NetworkState previousState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }
}

package com.example.vahe.newsfeed.screens;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.vahe.newsfeed.listener.BaseClickListener;
import com.example.vahe.newsfeed.model.BaseObject;
import com.example.vahe.newsfeed.screens.viewholders.BaseRecyclerViewHolder;
import com.example.vahe.newsfeed.screens.viewholders.ViewHolderProvider;

import java.util.List;

public class BaseAdapter<T extends BaseObject> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    private List<T> items;
    private BaseClickListener baseClickListener;
    private ViewHolderProvider<T> viewHolderProvider;

    private LayoutInflater inflater;
    private int viewMode;

    public BaseAdapter(LayoutInflater inflater, BaseClickListener baseClickListener, int viewMode) {
        this.inflater = inflater;
        this.baseClickListener = baseClickListener;
        viewHolderProvider = new ViewHolderProvider<>();
        this.viewMode = viewMode;
        viewHolderProvider.updateViewMode(viewMode);
    }

    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return viewHolderProvider.provideViewHolder(inflater, viewGroup, baseClickListener, items.get(i));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder baseRecyclerViewHolder, int i) {
        T obj = items.get(i);
        baseRecyclerViewHolder.bind(obj);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

}

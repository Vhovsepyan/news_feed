package com.example.vahe.newsfeed1.screens;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.vahe.newsfeed1.model.BaseObject;

import java.util.List;

public class BaseAdapterHelper<T extends BaseObject> extends RecyclerView.Adapter<BaseRecyclerViewHolder<T>> {
    private List<T> items;
    @NonNull
    @Override
    public BaseRecyclerViewHolder<T> onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder baseRecyclerViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }
}

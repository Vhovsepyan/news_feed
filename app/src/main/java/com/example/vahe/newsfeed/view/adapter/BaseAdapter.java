package com.example.vahe.newsfeed.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.vahe.newsfeed.listener.BaseClickListener;
import com.example.vahe.newsfeed.model.BaseObject;
import com.example.vahe.newsfeed.view.viewholders.BaseRecyclerViewHolder;
import com.example.vahe.newsfeed.view.viewholders.ViewHolderProvider;

import java.util.List;

public class BaseAdapter<T extends BaseObject> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    private List<T> items;
    private BaseClickListener baseClickListener;
    private ViewHolderProvider<T> viewHolderProvider;

    public BaseAdapter(BaseClickListener baseClickListener, int viewMode) {
        this.baseClickListener = baseClickListener;
        viewHolderProvider = new ViewHolderProvider<>();
        viewHolderProvider.updateViewMode(viewMode);
    }

    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
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

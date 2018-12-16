package com.example.vahe.newsfeed.screens.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.model.BaseObject;
import com.example.vahe.newsfeed.screens.BaseClickListener;
import com.example.vahe.newsfeed.screens.BaseRecyclerViewHolder;

import java.util.List;

public class NewsItemAdapter<T extends BaseObject> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    private List<T> items;
    private BaseClickListener baseClickListener;

    private LayoutInflater inflater;

    public NewsItemAdapter(LayoutInflater inflater, BaseClickListener baseClickListener) {
        this.inflater = inflater;
        this.baseClickListener = baseClickListener;
    }

    public void setItems(List<T> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new NewsItemViewHolder(
                inflater.inflate(R.layout.news_item_layout, viewGroup, false),
                baseClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder baseRecyclerViewHolder, int i) {
        T obj = items.get(i);
        baseRecyclerViewHolder.bind(obj);
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getObjectType();
    }

    @Override
    public int getItemCount() {
        return items == null ? 0: items.size();
    }
}

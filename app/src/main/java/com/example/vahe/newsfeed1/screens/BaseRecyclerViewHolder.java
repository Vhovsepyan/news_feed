package com.example.vahe.newsfeed1.screens;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.vahe.newsfeed1.model.BaseObject;

public abstract class BaseRecyclerViewHolder<T extends BaseObject> extends RecyclerView.ViewHolder {
    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(BaseObject object);

    // Disable touch detection for parent recyclerView if we use vertical nested recyclerViews
    @SuppressLint("ClickableViewAccessibility")
    protected View.OnTouchListener mTouchListener = (v, event) -> {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        return false;
    };
}

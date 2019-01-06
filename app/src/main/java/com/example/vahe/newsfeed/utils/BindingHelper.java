package com.example.vahe.newsfeed.utils;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.vahe.newsfeed.listener.EndlessRecyclerViewScrollListener;
import com.example.vahe.newsfeed.listener.OnLoadMoreListener;

public class BindingHelper {

    @BindingAdapter({"initStaggeredRecyclerView", "loadMoreListener"})
    public static void initStaggeredRecyclerView(RecyclerView recyclerView, RecyclerView.Adapter adapter, OnLoadMoreListener loadMoreListener) {
        StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(llm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadMoreListener.onLoadMore();
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
        recyclerView.setAdapter(adapter);
    }


    @BindingAdapter({"initHorizontalRecyclerView"})
    public static void initHorizontalRecyclerView(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        LinearLayoutManager llm = new LinearLayoutManager(recyclerView.getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"src"})
    public static void loadImage(ImageView view, String url) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(view.getContext())
                    .load(url)
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(view);
        }
    }

    @BindingAdapter({"srcCircle"})
    public static void loadImageCircle(ImageView view, String url) {
        Glide.with(view.getContext())
                .asBitmap()
                .apply(new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL))
                .load(url)
                .into(new BitmapImageViewTarget(view) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(view.getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        view.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    @BindingAdapter({"setHTMLText"})
    public static void setText(TextView textView, String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView.setText(Html.fromHtml(text));
        }
    }

    @BindingAdapter({"visibility"})
    public static void visibility(View view, boolean isVisibile) {
        if (isVisibile) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}

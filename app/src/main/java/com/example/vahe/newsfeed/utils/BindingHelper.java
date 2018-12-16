package com.example.vahe.newsfeed.utils;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

public class BindingHelper {

    @BindingAdapter({"initVerticalRecyclerView"})
    public static void initVerticalRecyclerView(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        LinearLayoutManager llm = new LinearLayoutManager(recyclerView.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
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
}

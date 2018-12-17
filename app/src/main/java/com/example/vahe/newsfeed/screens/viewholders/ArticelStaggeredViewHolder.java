package com.example.vahe.newsfeed.screens.viewholders;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.ImageView;

import com.example.vahe.newsfeed.BR;
import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.listener.BaseClickListener;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.BaseObject;

import java.util.Random;

public class ArticelStaggeredViewHolder extends BaseRecyclerViewHolder {

    private ViewDataBinding binding;
    private BaseClickListener baseClickListener;

    public ArticelStaggeredViewHolder(View itemView, BaseClickListener baseClickListener) {
        super(itemView);
        this.baseClickListener = baseClickListener;
        binding = DataBindingUtil.bind(itemView);
    }

    @Override
    public void bind(BaseObject object) {
        Article article = (Article) object;
        getBinding().setVariable(BR.item, article);
        getBinding().setVariable(BR.holder, this);
        itemView.setOnClickListener(view -> baseClickListener.onItemClickListener(article));
        ImageView imageView = itemView.findViewById(R.id.avatar);
        imageView.getLayoutParams().height = getRandomHeight();
        imageView.getLayoutParams().width = getRandomWidth();
        getBinding().executePendingBindings();
    }

    @Override
    public ViewDataBinding getBinding() {
        return binding;
    }

    private int getRandomHeight() {
        Random r = new Random();
        int low = 200;
        int high = 400;
        return r.nextInt(high - low) + low;
    }

    private int getRandomWidth() {
        Random r = new Random();
        int low = 400;
        int high = 500;
        return r.nextInt(high - low) + low;
    }
}

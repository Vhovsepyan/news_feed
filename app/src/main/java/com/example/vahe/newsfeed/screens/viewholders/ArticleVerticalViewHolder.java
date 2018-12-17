package com.example.vahe.newsfeed.screens.viewholders;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;
import com.example.vahe.newsfeed.BR;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.model.BaseObject;
import com.example.vahe.newsfeed.listener.BaseClickListener;

public class ArticleVerticalViewHolder extends BaseRecyclerViewHolder {
    private ViewDataBinding binding;
    private BaseClickListener baseClickListener;

    public ArticleVerticalViewHolder(View itemView, BaseClickListener baseClickListener) {
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
        getBinding().executePendingBindings();
    }

    @Override
    public ViewDataBinding getBinding() {
        return binding;
    }
}

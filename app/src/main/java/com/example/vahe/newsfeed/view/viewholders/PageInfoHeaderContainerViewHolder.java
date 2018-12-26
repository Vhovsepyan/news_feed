package com.example.vahe.newsfeed.view.viewholders;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.example.vahe.newsfeed.BR;
import com.example.vahe.newsfeed.model.BaseObject;
import com.example.vahe.newsfeed.model.PageInfo;

public class PageInfoHeaderContainerViewHolder extends BaseRecyclerViewHolder<PageInfo> {
    private ViewDataBinding binding;


    public PageInfoHeaderContainerViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    @Override
    public void bind(BaseObject object) {
        PageInfo pageInfo = (PageInfo) object;
        getBinding().setVariable(BR.object, pageInfo);
        getBinding().executePendingBindings();

    }

    @Override
    public ViewDataBinding getBinding() {
        return binding;
    }
}

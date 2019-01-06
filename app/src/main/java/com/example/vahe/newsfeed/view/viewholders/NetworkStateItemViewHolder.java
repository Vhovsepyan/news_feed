package com.example.vahe.newsfeed.view.viewholders;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.example.vahe.newsfeed.BR;
import com.example.vahe.newsfeed.model.BaseObject;
import com.example.vahe.newsfeed.model.NetworkState;

public class NetworkStateItemViewHolder extends BaseRecyclerViewHolder {
    public ObservableBoolean isProgressBarVisible = new ObservableBoolean(false);
    public ObservableBoolean isErrorMessageVisible = new ObservableBoolean(false);
    private ViewDataBinding binding;

    public NetworkStateItemViewHolder(View itemView) {
        super(itemView);
        this.binding = DataBindingUtil.bind(itemView);
    }

    @Override
    public void bind(BaseObject object) {
        NetworkState networkState = (NetworkState) object;
        if (networkState != null && networkState.getStatus() == NetworkState.Status.RUNNING) {
            isProgressBarVisible.set(true);
        } else {
            isProgressBarVisible.set(false);
        }

        if (networkState != null && networkState.getStatus() == NetworkState.Status.FAILED) {
            isErrorMessageVisible.set(true);
        } else {
            isErrorMessageVisible.set(false);
        }

        binding.setVariable(BR.holder, this);
        binding.setVariable(BR.item, networkState);
        binding.executePendingBindings();
    }

    @Override
    public ViewDataBinding getBinding() {
        return binding;
    }
}

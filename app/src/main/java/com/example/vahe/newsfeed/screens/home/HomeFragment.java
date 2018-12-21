package com.example.vahe.newsfeed.screens.home;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.vahe.newsfeed.BR;
import com.example.vahe.newsfeed.NewsFeedApp;
import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.databinding.HomeFragmentBinding;
import com.example.vahe.newsfeed.screens.BaseFragment;
import com.example.vahe.newsfeed.screens.BaseVM;
import com.example.vahe.newsfeed.utils.Constants;

public class HomeFragment extends BaseFragment<HomeFragmentBinding> {
    private PageInfoVM viewModel;
    private RecyclerView listRecyclerView;
    private RecyclerView staggeredRecyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected BaseVM onCreateViewModel() {
        viewModel = new PageInfoVM(getAppContext());
        NewsFeedApp app = (NewsFeedApp) getAppContext();
        app.appComponent().inject(viewModel);
        viewModel.init();
        return viewModel;
    }

    @Override
    protected BaseVM onBindViewModel(HomeFragmentBinding binding) {
        listRecyclerView = binding.listRecyclerView;
        staggeredRecyclerView = binding.staggeredRecyclerView;
        return viewModel;
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        viewModel.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        viewModel.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        viewModel.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Parcelable parcelableList = listRecyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(Constants.LIST_RECYCLER_VIEW_KEY, parcelableList);
        Parcelable parcelableStaggered = staggeredRecyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(Constants.STAGGERED_RECYCLER_VIEW_KEY, parcelableStaggered);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            Parcelable parcelableList = savedInstanceState.getParcelable(Constants.LIST_RECYCLER_VIEW_KEY);
            listRecyclerView.getLayoutManager().onRestoreInstanceState(parcelableList);

            Parcelable parcelableStaggered = savedInstanceState.getParcelable(Constants.STAGGERED_RECYCLER_VIEW_KEY);
            staggeredRecyclerView.getLayoutManager().onRestoreInstanceState(parcelableStaggered);
        }
    }
}

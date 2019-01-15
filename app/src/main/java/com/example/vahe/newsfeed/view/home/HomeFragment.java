package com.example.vahe.newsfeed.view.home;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.vahe.newsfeed.BR;
import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.databinding.HomeFragmentBinding;
import com.example.vahe.newsfeed.listener.BaseClickListener;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.utils.Constants;
import com.example.vahe.newsfeed.view.BaseFragment;
import com.example.vahe.newsfeed.view.BaseVM;
import com.example.vahe.newsfeed.view.adapter.ArticleAdapter;
import com.example.vahe.newsfeed.view.info.ArticleInfoFragment;

public class HomeFragment extends BaseFragment<HomeFragmentBinding> {
    private ArticleListViewModel viewModel;
    private RecyclerView listRecyclerView;
    private RecyclerView staggeredRecyclerView;
    private ArticleAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ArticleAdapter(baseClickListener);

        viewModel.getArticleLiveData().observe(this, pagedList -> {
            adapter.submitList(pagedList);
        });

        viewModel.getNetworkState().observe(this, networkState -> {
            adapter.setNetworkState(networkState);
        });

        listRecyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
        listRecyclerView.setAdapter(adapter);

    }

    private BaseClickListener baseClickListener = obj -> {
        Bundle bundle = new Bundle();
        bundle.putString(ArticleInfoFragment.BUNDLE_ARTICLE_ID_KEY_INFO, ((Article) obj).getId());
        getNavController().navigate(R.id.infoFragment, bundle);
    };

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected ArticleListViewModel onCreateViewModel() {
        viewModel = ViewModelProviders.of(this).get(ArticleListViewModel.class);
        return viewModel;
    }

    @Override
    protected BaseVM onBindViewModel(HomeFragmentBinding binding) {
        listRecyclerView = binding.listRecyclerView;
        return null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Parcelable parcelableList = listRecyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(Constants.LIST_RECYCLER_VIEW_KEY, parcelableList);
/*        Parcelable parcelableStaggered = staggeredRecyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(Constants.STAGGERED_RECYCLER_VIEW_KEY, parcelableStaggered);*/
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            Parcelable parcelableList = savedInstanceState.getParcelable(Constants.LIST_RECYCLER_VIEW_KEY);
            listRecyclerView.getLayoutManager().onRestoreInstanceState(parcelableList);

/*            Parcelable parcelableStaggered = savedInstanceState.getParcelable(Constants.STAGGERED_RECYCLER_VIEW_KEY);
            staggeredRecyclerView.getLayoutManager().onRestoreInstanceState(parcelableStaggered);*/
        }
    }
}

package com.example.vahe.newsfeed.view.info;

import com.example.vahe.newsfeed.NewsFeedApp;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.repository.ArticleRepository;
import com.example.vahe.newsfeed.view.BaseVM;

import javax.inject.Inject;

public class ArticleVM extends BaseVM {

    @Inject
    public ArticleRepository articleRepository;
    public Article article;

    public ArticleVM(NewsFeedApp app) {
        super(app);
    }
/*
    @Override
    protected void onViewCreated(View view, Bundle bundle, ViewDataBinding binding) {
        super.onViewCreated(view, bundle, binding);
        if (bundle != null && bundle.containsKey(ArticleInfoFragment.BUNDLE_ARTICLE_ID_KEY_INFO)) {
            Article article = bundle.getParcelable(ArticleInfoFragment.BUNDLE_ARTICLE_ID_KEY_INFO);
            //ToDo need to change
            if (article != null){
                Article newArticle = null;
                try {
                    newArticle = (Article) article.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
//                setArticle(newArticle);
                binding.setVariable(BR.articleItem, this.article);
            }
        }
    }

    @Override
    protected void init() {

    }

    @Bindable
    public Article getArticle() {
        return article;
    }
*//*
    public void setArticle(Article article) {
        this.article = article;
        notifyPropertyChanged(BR.viewModel);
    }*//*

    @Override
    protected void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.article_info_menu, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem menuItem = menu.findItem(R.id.action_pin);
        if (article.isPinned()) {
            menuItem.setTitle(getString(R.string.pinned_text));
        } else {
            menuItem.setTitle(getString(R.string.pin_text));
        }
    }

    @Override
    protected void onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_pin: {
                handelPinAction(item, article.isPinned());
            }
        }
    }

    private void handelPinAction(MenuItem menuItem, boolean isPinned) {
        if (isPinned) {
            article.setPinned(false);
            articleRepository.delete(article);
            menuItem.setTitle(getString(R.string.pin_text));
        } else {
            article.setPinned(true);
            articleRepository.insert(article);
            menuItem.setTitle(getString(R.string.pinned_text));
        }
    }*/
}
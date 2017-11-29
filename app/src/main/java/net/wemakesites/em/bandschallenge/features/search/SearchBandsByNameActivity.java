package net.wemakesites.em.bandschallenge.features.search;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

import net.wemakesites.em.bandschallenge.R;
import net.wemakesites.em.bandschallenge.features.base.BaseActivity;
import net.wemakesites.em.bandschallenge.features.common.ErrorView;
import net.wemakesites.em.bandschallenge.injection.component.ActivityComponent;
import net.wemakesites.em.bandschallenge.utils.UiUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class SearchBandsByNameActivity extends BaseActivity implements SearchByNameView {

    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;

    @BindView(R.id.errorView)
    ErrorView errorView;

    @BindView(R.id.recentSearchesRecyclerView)
    RecyclerView recentSearchesRecyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @Inject
    SearchByNamePresenter presenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_search_band_by_name;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        presenter.attachView(this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void detachPresenter() {
        presenter.detachView();
    }

    @Override
    public void showProgressBar() {
        UiUtils.showView(progressBar);
    }

    @Override
    public void hideProgressBar() {
        UiUtils.hideView(progressBar);
    }

    @Override
    public void showErrorView() {
        UiUtils.showView(errorView);
    }

    @Override
    public void hideErrorView() {
        UiUtils.hideView(errorView);
    }
}

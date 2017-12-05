package net.wemakesites.em.bandschallenge.features.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

import net.wemakesites.em.bandschallenge.R;
import net.wemakesites.em.bandschallenge.data.model.response.search.SearchResult;
import net.wemakesites.em.bandschallenge.features.base.AbstractBaseActivity;
import net.wemakesites.em.bandschallenge.features.details.DetailsActivity;
import net.wemakesites.em.bandschallenge.injection.component.ActivityComponent;
import net.wemakesites.em.bandschallenge.utils.KeyboardUtil;
import net.wemakesites.em.bandschallenge.utils.UiUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class SearchBandsByNameActivity extends AbstractBaseActivity implements SearchByNameView {


    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;

    @BindView(R.id.autoCompleteProgressBar)
    ProgressBar progressBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Inject
    SearchByNamePresenter presenter;


    @Override
    protected int getLayout() {
        return R.layout.activity_search_band_by_name;
    }

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        presenter.attachView(this);
    }


    @Override
    protected void initViews(final Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        presenter.addOnItemClickedSubscriber();
        presenter.addOnTextChangedSubscriber();

    }

    @Override
    protected void detachPresenter() {
        presenter.detachView();
    }


    @Override
    public AutoCompleteTextView getAutoCompleteTextView() {
        return autoCompleteTextView;
    }

    @Override
    public void showAutoCompleteProgressBar() {
        UiUtils.showView(progressBar);
    }

    @Override
    public void hideAutoCompleteProgressBar() {
        UiUtils.hideView(progressBar);
    }

    @Override
    public void displaySearchResult(final List<SearchResult> searchResults) {

        final ArrayAdapter<SearchResult> itemsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, searchResults);
        autoCompleteTextView.setAdapter(itemsAdapter);

        final String enteredText = autoCompleteTextView.getText().toString();
        if (!searchResults.isEmpty() && enteredText.equals(searchResults.get(0).getName())) {
            autoCompleteTextView.dismissDropDown();
        } else {
            autoCompleteTextView.showDropDown();
        }
    }

    @Override
    public void errorLoadingSearchResults(final Throwable e) {
        KeyboardUtil.hideKeyboard(this);
        UiUtils.showError(this, R.string.error_message);
    }

    @Override
    public void bandItemClicked(final SearchResult searchResult) {
        presenter.saveInHistory(searchResult);
        showDetails(searchResult);
    }


    private void showDetails(final SearchResult searchResult) {
       final Intent intent = DetailsActivity.getStartIntent(this,
                searchResult.getId(),
                searchResult.getName());
       startActivity(intent);

    }

}

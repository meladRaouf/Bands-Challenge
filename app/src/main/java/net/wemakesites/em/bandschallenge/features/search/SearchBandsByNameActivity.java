package net.wemakesites.em.bandschallenge.features.search;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

import net.wemakesites.em.bandschallenge.R;
import net.wemakesites.em.bandschallenge.data.model.response.search.SearchResponse;
import net.wemakesites.em.bandschallenge.data.model.response.search.SearchResult;
import net.wemakesites.em.bandschallenge.features.base.BaseActivity;
import net.wemakesites.em.bandschallenge.injection.component.ActivityComponent;
import net.wemakesites.em.bandschallenge.utils.KeyboardUtil;
import net.wemakesites.em.bandschallenge.utils.UiUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class SearchBandsByNameActivity extends BaseActivity implements SearchByNameView {


    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;

    @BindView(R.id.autoCompleteProgressBar)
    ProgressBar autoCompleteProgressBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Inject
    SearchByNamePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.addOnAutoCompleteTextViewItemClickedSubscriber();
        presenter.addOnAutoCompleteTextViewTextChangedObserver();
    }


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
        setSupportActionBar(toolbar);

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
        UiUtils.showView(autoCompleteProgressBar);
    }

    @Override
    public void hideAutoCompleteProgressBar() {
        UiUtils.hideView(autoCompleteProgressBar);
    }

    @Override
    public void displaySearchResult(List<SearchResult> searchResults) {

        ArrayAdapter<SearchResult> itemsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, searchResults);
        autoCompleteTextView.setAdapter(itemsAdapter);

        String enteredText = autoCompleteTextView.getText().toString();
        if (searchResults.size() >= 1 && enteredText.equals(searchResults.get(0).getName())) {
            autoCompleteTextView.dismissDropDown();
        } else {
            autoCompleteTextView.showDropDown();
        }
    }

    @Override
    public void errorLoadingSearchResults(Throwable e) {
        KeyboardUtil.hideKeyboard(this);
        UiUtils.showError(this, R.string.error_message);
    }

    @Override
    public void bandItemClicked(SearchResult searchResult) {
        presenter.saveInHistory(searchResult);
        showDetails(searchResult.getId());
    }


    private void showDetails(long bandId) {
        //TODO start activity details
    }

}

package net.wemakesites.em.bandschallenge.features.search;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

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

    @BindView(R.id.toolbar)
    Toolbar toolbar;



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
        setSupportActionBar(toolbar);
        presenter.addOnAutoCompleteTextViewItemClickedSubscriber();
        presenter.addOnAutoCompleteTextViewTextChangedObserver();

    }


    @Override
    protected void initViews(Bundle savedInstanceState) {

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
    public void displaySearchResult(SearchResponse searchResponse) {
        List<SearchResult>  list = searchResponse.getData().getSearchResults();

        ArrayAdapter<SearchResult> itemsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);
        autoCompleteTextView.setAdapter(itemsAdapter);

        String enteredText = autoCompleteTextView.getText().toString();
        if (list.size() >= 1 && enteredText.equals(list.get(0).getName())) {
            autoCompleteTextView.dismissDropDown();
        } else {
            autoCompleteTextView.showDropDown();
        }
    }

    @Override
    public void errorLoadingSearchResults(Throwable e) {
        KeyboardUtil.hideKeyboard(this);
        UiUtils.showError(this,R.string.error_message);
    }

    @Override
    public void bandItemClicked(SearchResult searchResult) {
        Log.d("Band clicked",searchResult.getName());
    }
}

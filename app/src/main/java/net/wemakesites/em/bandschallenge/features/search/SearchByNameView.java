package net.wemakesites.em.bandschallenge.features.search;

import android.widget.AutoCompleteTextView;

import net.wemakesites.em.bandschallenge.data.model.response.search.SearchResult;
import net.wemakesites.em.bandschallenge.features.base.MvpView;

import java.util.List;

interface SearchByNameView extends MvpView {

    AutoCompleteTextView getAutoCompleteTextView();

    void displaySearchResult(List<SearchResult> searchResponse);

    void errorLoadingSearchResults(Throwable e);

    void bandItemClicked(SearchResult searchResult);

    void showAutoCompleteProgressBar();

    void hideAutoCompleteProgressBar();
}

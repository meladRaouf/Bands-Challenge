package net.wemakesites.em.bandschallenge.features.search;

import android.widget.AutoCompleteTextView;

import net.wemakesites.em.bandschallenge.data.model.response.search.SearchResponse;
import net.wemakesites.em.bandschallenge.data.model.response.search.SearchResult;
import net.wemakesites.em.bandschallenge.features.base.MvpView;

interface SearchByNameView extends MvpView {

    AutoCompleteTextView getAutoCompleteTextView();


    void displaySearchResult(SearchResponse searchResponse);

    void errorLoadingSearchResults(Throwable e);

    void bandItemClicked(SearchResult searchResult);
}

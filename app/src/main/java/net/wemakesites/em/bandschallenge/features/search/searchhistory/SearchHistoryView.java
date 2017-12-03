package net.wemakesites.em.bandschallenge.features.search.searchhistory;


import net.wemakesites.em.bandschallenge.data.model.local.SearchHistoryItem;
import net.wemakesites.em.bandschallenge.features.base.MvpView;

interface SearchHistoryView extends MvpView {

    SearchHistoryItemsAdapter getAdapter();

    void showDetails( SearchHistoryItem searchHistoryItem);
}

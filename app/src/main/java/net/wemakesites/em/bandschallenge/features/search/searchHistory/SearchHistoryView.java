package net.wemakesites.em.bandschallenge.features.search.searchHistory;


import net.wemakesites.em.bandschallenge.features.base.MvpView;

interface SearchHistoryView extends MvpView {

    SearchHistoryItemsAdapter getAdapter();

    void showDetails(long bandId);
}

package net.wemakesites.em.bandschallenge.features.search.searchhistory;


import net.wemakesites.em.bandschallenge.features.base.MvpView;

interface SearchHistoryView extends MvpView {

    SearchHistoryItemsAdapter getAdapter();

    void showDetails(long bandId);
}

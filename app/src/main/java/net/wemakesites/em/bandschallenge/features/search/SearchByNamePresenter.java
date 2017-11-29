package net.wemakesites.em.bandschallenge.features.search;

import net.wemakesites.em.bandschallenge.data.DataManager;
import net.wemakesites.em.bandschallenge.features.base.BasePresenter;

import javax.inject.Inject;


class SearchByNamePresenter extends BasePresenter<SearchByNameView> {
    private final DataManager dataManager;

    @Inject
    public SearchByNamePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}

package net.wemakesites.em.bandschallenge.features.search;

import net.wemakesites.em.bandschallenge.features.base.MvpView;

interface SearchByNameView extends MvpView {
    void showProgressBar();

    void hideProgressBar();

    void showErrorView();

    void hideErrorView();


}

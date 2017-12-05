package net.wemakesites.em.bandschallenge.features.search.searchhistory;


import net.wemakesites.em.bandschallenge.data.DataManager;
import net.wemakesites.em.bandschallenge.data.model.local.SearchHistoryItem;
import net.wemakesites.em.bandschallenge.features.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;


public class SearchHistoryPresenter extends BasePresenter<SearchHistoryView> {

    private final DataManager dataManager;
    private SearchHistoryView view;


    @Inject
    SearchHistoryPresenter(final DataManager dataManager) {
        super();
        this.dataManager = dataManager;
    }


    @Override
    public void attachView(final SearchHistoryView mvpView) {
        super.attachView(mvpView);
        view = getView();

    }

    void addHistoryChangeSubscriber() {
        addDisposable(dataManager.getHistory()
                .mapToList(SearchHistoryItem.MAPPER)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view.getAdapter())
        );

    }

    void addRecyclerViewOnItemClickSubscriber() {
        addDisposable(view.getAdapter()
                .getItemClickedObservable()
                .subscribe(searchHistoryItem -> view.showDetails(searchHistoryItem)));
    }
}

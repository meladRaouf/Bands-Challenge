package net.wemakesites.em.bandschallenge.features.search.searchHistory;


import net.wemakesites.em.bandschallenge.data.DataManager;
import net.wemakesites.em.bandschallenge.data.model.local.SearchHistoryItem;
import net.wemakesites.em.bandschallenge.features.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;


public class SearchHistoryPresenter extends BasePresenter<SearchHistoryView> {

    private final DataManager dataManager;
    private SearchHistoryView view;


    @Inject
    SearchHistoryPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void attachView(SearchHistoryView mvpView) {
        super.attachView(mvpView);
        view = getView();

    }

    public void addLocalSearchHistoryChangesObserver() {
        addDisposable(dataManager.getHistory()
                .mapToList(SearchHistoryItem.MAPPER)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view.getAdapter())
        );

    }

    public void addRecyclerViewOnItemClickObserver() {
        addDisposable(
                view.getAdapter()
                        .getItemClickedObservable()
                        .subscribe(id -> view.showDetails(id)));
    }
}

package net.wemakesites.em.bandschallenge.features.search;


import android.util.Log;

import com.jakewharton.rxbinding2.widget.RxAutoCompleteTextView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import net.wemakesites.em.bandschallenge.data.DataManager;
import net.wemakesites.em.bandschallenge.data.model.response.search.SearchResponse;
import net.wemakesites.em.bandschallenge.data.model.response.search.SearchResult;
import net.wemakesites.em.bandschallenge.features.base.BasePresenter;


import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


class SearchByNamePresenter extends BasePresenter<SearchByNameView> {

    private static final long DELAY_IN_MILLIS = 500;
    private static final int MIN_LENGTH_TO_START = 1;
    private static final int NUMBER_OF_RETRIES = 2;

    private final DataManager dataManager;
    private SearchByNameView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    SearchByNamePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(SearchByNameView mvpView) {
        super.attachView(mvpView);
        view = getView();

    }

    void addOnAutoCompleteTextViewTextChangedObserver() {
        Observable<SearchResponse> autocompleteResponseObservable;
        autocompleteResponseObservable = RxTextView.textChangeEvents(view.getAutoCompleteTextView())
                .debounce(DELAY_IN_MILLIS, TimeUnit.MILLISECONDS)
                .map(textViewTextChangeEvent -> textViewTextChangeEvent.text().toString())
                .filter(s -> s.length() >= MIN_LENGTH_TO_START)
                .observeOn(Schedulers.io())
                .switchMap(dataManager::searchBands)
                .observeOn(AndroidSchedulers.mainThread())
                .retry(NUMBER_OF_RETRIES);

        compositeDisposable.add(autocompleteResponseObservable.subscribe(
                searchResponse -> view.displaySearchResult(searchResponse),
                e -> view.errorLoadingSearchResults(e)
        ));


    }

    public void addOnAutoCompleteTextViewItemClickedSubscriber() {
        Observable<SearchResult> adapterViewItemClickEventObservable =
                RxAutoCompleteTextView.itemClickEvents(view.getAutoCompleteTextView())
                        .map(adapterViewItemClickEvent -> (SearchResult) view.getAutoCompleteTextView().getAdapter()
                                .getItem(adapterViewItemClickEvent.position()))
                        .observeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

        compositeDisposable.add(
                adapterViewItemClickEventObservable.subscribe(
                        view::bandItemClicked));
    }

    @Override
    public void detachView() {
        super.detachView();
        compositeDisposable.dispose();

    }
}

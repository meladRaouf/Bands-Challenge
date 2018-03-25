package net.wemakesites.em.bandschallenge.features.search;


import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.widget.RxAutoCompleteTextView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import net.wemakesites.em.bandschallenge.data.DataManager;
import net.wemakesites.em.bandschallenge.data.model.response.search.SearchResponse;
import net.wemakesites.em.bandschallenge.data.model.response.search.SearchResult;
import net.wemakesites.em.bandschallenge.features.base.BasePresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


class SearchByNamePresenter extends BasePresenter<SearchByNameView> {

    private static final long DELAY_IN_MILLIS = 500;
    private static final int MIN_LENGTH_TO_START = 1;
    private static final int NUMBER_OF_RETRIES = 2;

    private final DataManager dataManager;
    private SearchByNameView view;


    @Inject
    SearchByNamePresenter(final DataManager dataManager) {
        super();
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(final SearchByNameView mvpView) {
        super.attachView(mvpView);
        view = getView();

    }


    void addOnItemClickedSubscriber() {
        final Observable<SearchResult> adapterViewItemClickEventObservable =
                RxAutoCompleteTextView.itemClickEvents(view.getAutoCompleteTextView())
                        .map(adapterViewItemClickEvent ->
                                (SearchResult) view.getAutoCompleteTextView().getAdapter()
                                        .getItem(adapterViewItemClickEvent.position()))
                        .observeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        addDisposable(adapterViewItemClickEventObservable.subscribe(view::bandItemClicked));
    }


    void addOnTextChangedSubscriber() {
        InitialValueObservable<TextViewTextChangeEvent> textChangeObservable =
                RxTextView.textChangeEvents(view.getAutoCompleteTextView());
        addDisposable(getProgressBarSubscriber(textChangeObservable));
        addDisposable(getSearchResponseSubscriber(textChangeObservable));
    }

    private Disposable getProgressBarSubscriber(
            final InitialValueObservable<TextViewTextChangeEvent> textChangeObservable) {
        return textChangeObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.text().length() > 0) {
                        view.showAutoCompleteProgressBar();
                    }
                });

    }

    private Disposable getSearchResponseSubscriber(final InitialValueObservable<TextViewTextChangeEvent> textChangeObservable) {
        final Observable<SearchResponse> searchResponseObservable =
                textChangeObservable
                        .debounce(DELAY_IN_MILLIS, TimeUnit.MILLISECONDS)
                        .map(textViewTextChangeEvent -> textViewTextChangeEvent.text().toString())
                        .filter(s -> s.length() >= MIN_LENGTH_TO_START)
                        .observeOn(Schedulers.io())
                        .switchMap(dataManager::searchBands)
                        .observeOn(AndroidSchedulers.mainThread())
                        .retry(NUMBER_OF_RETRIES);
        return searchResponseObservable.
                subscribe(this::searchCompleted
                        , this::searchError

                );


    }


    private void searchCompleted(SearchResponse searchResponse) {
        view.displaySearchResult(searchResponse.getResponseData().getSearchResults());
        view.hideAutoCompleteProgressBar();
    }

    private void searchError(Throwable e) {
        view.errorLoadingSearchResults(e);
    }

    void saveInHistory(final SearchResult searchResult) {
        dataManager.saveInHistory(searchResult);
    }

}

package net.wemakesites.em.bandschallenge.features.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Base class that implements the Presenter interface and provides
 * a base implementation for attachView() and detachView().
 * It also handles keeping a reference to the mvpView that can be
 * accessed from the children classes by calling getView().
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private T mvpView;

    @Override
    public void attachView(final T mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void detachView() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }


    protected T getView() {
        return mvpView;
    }

    protected void addDisposable(final Disposable disposable) {
        compositeDisposable.add(disposable);
    }



}

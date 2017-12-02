package net.wemakesites.em.bandschallenge.features.base;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that can be
 * accessed from the children classes by calling getView().
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private T mvpView;

    @Override
    public void attachView(T mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void detachView() {
        mvpView = null;
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    protected boolean isViewAttached() {
        return mvpView != null;
    }

    protected T getView() {
        return mvpView;
    }

    protected void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    private static class MvpViewNotAttachedException extends RuntimeException {
        MvpViewNotAttachedException() {
            super(
                    "Please call Presenter.attachView(MvpView) before"
                            + " requesting data to the Presenter");
        }
    }


}

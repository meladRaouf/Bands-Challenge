package net.wemakesites.em.bandschallenge.features.details;

import net.wemakesites.em.bandschallenge.data.DataManager;
import net.wemakesites.em.bandschallenge.features.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


class DetailsPresenter extends BasePresenter<DetailsView> {

    private final DataManager dataManager;
    private DetailsView view;



    @Inject
    DetailsPresenter(final DataManager dataManager) {
        super();
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(DetailsView mvpView) {
        super.attachView(mvpView);
        view = getView();
    }

     void loadBandDetails(long bandId) {
        view.showProgress();

        addDisposable(dataManager.getBand(bandId)
                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bandDetailsResponse -> {
                            view.hideProgress();
                            view.showBandData(bandDetailsResponse.getBandData());
                        },
                        e -> {
                            view.hideProgress();
                            view.showError(e);
                        })
        );

    }
}

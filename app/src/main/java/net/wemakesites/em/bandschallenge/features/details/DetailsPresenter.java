package net.wemakesites.em.bandschallenge.features.details;

import net.wemakesites.em.bandschallenge.data.DataManager;
import net.wemakesites.em.bandschallenge.data.model.response.banddetails.BandDetailsResponse;
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
    public void attachView(final DetailsView mvpView) {
        super.attachView(mvpView);
        view = getView();
    }

    void loadBandDetails(final long bandId) {
        view.showProgress();

        addDisposable(dataManager.getBand(bandId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::detailsCompleted,
                        this::detailsError
                )
        );

    }

    private void detailsError(Throwable e) {
        view.hideProgress();
        view.showErrorAndRetryButton();
    }


    private void detailsCompleted(BandDetailsResponse bandDetailsResponse) {
        view.hideProgress();
        view.showBandData(bandDetailsResponse.getBandData());
    }
}

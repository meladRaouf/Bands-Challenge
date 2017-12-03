package net.wemakesites.em.bandschallenge.features.details;

import net.wemakesites.em.bandschallenge.data.model.response.banddetails.BandData;
import net.wemakesites.em.bandschallenge.features.base.MvpView;



 interface DetailsView extends MvpView {
     void showProgress();

     void hideProgress();

     void showBandData(BandData bandData);

     void showError(Throwable e);
 }

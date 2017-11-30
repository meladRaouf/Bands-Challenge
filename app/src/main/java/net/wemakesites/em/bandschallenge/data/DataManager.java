package net.wemakesites.em.bandschallenge.data;


import com.squareup.sqlbrite3.BriteDatabase;

import net.wemakesites.em.bandschallenge.data.model.response.bandDetails.BandData;
import net.wemakesites.em.bandschallenge.data.model.response.search.SearchResponse;
import net.wemakesites.em.bandschallenge.data.remote.BandsService;


import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;


@Singleton
public class DataManager {

    private final BriteDatabase localDb;
    private BandsService bandsService;

    @Inject
    public DataManager(BandsService bandsService, BriteDatabase localDb) {
        this.bandsService = bandsService;
        this.localDb = localDb;
    }

    public Observable<SearchResponse> searchBands(String keyword) {
        return bandsService
                .searchBands(keyword);


    }

    public Single<BandData> getBand(String bandId) {
        return bandsService
                .getBand(bandId)
                .map(namedResource -> namedResource.getBandData())
                .firstOrError();
    }
}

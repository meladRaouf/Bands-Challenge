package net.wemakesites.em.bandschallenge.data;

import net.wemakesites.em.bandschallenge.data.model.response.bandDetails.BandData;
import net.wemakesites.em.bandschallenge.data.model.response.search.SearchResult;
import net.wemakesites.em.bandschallenge.data.remote.BandsService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;


@Singleton
public class DataManager {

    private BandsService bandsService;

    @Inject
    public DataManager(BandsService bandsService) {
        this.bandsService = bandsService;
    }

    public Single<List<SearchResult>> getPokemonList(String keyword) {

        return bandsService
                .searchBands(keyword)
                .toObservable()
                .flatMapIterable(namedResources -> namedResources.getData().getSearchResults())
                .map(namedResource -> namedResource)
                .toList();

    }

    public Single<BandData> getBand(String bandId) {
        return bandsService
                .getBand(bandId)
                .toObservable()
                .map(namedResource -> namedResource.getBandData())
                .firstOrError();
    }
}

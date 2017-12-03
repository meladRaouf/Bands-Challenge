package net.wemakesites.em.bandschallenge.data;


import com.squareup.sqlbrite3.BriteDatabase;
import com.squareup.sqlbrite3.QueryObservable;

import net.wemakesites.em.bandschallenge.data.model.local.SearchHistoryItem;
import net.wemakesites.em.bandschallenge.data.model.response.banddetails.BandDetailsResponse;
import net.wemakesites.em.bandschallenge.data.model.response.search.SearchResponse;
import net.wemakesites.em.bandschallenge.data.model.response.search.SearchResult;
import net.wemakesites.em.bandschallenge.data.remote.BandsService;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE;


@Singleton
public class DataManager {

    private final BriteDatabase localDb;
    private final BandsService bandsService;

    @Inject
    DataManager(final BandsService bandsService, final BriteDatabase localDb) {
        this.bandsService = bandsService;
        this.localDb = localDb;
    }

    public Observable<SearchResponse> searchBands(final String keyword) {
        return bandsService.searchBands(keyword);
    }

    public Observable<BandDetailsResponse> getBand(final String bandId) {
        return bandsService.getBand(bandId);
    }

    public void saveInHistory(final SearchResult searchResult) {
        localDb.insert(SearchHistoryItem.TABLE, CONFLICT_REPLACE,
                SearchHistoryItem.convertToLocalItem(searchResult));
    }
    public QueryObservable getHistory(){
        return localDb.createQuery(SearchHistoryItem.TABLE,SearchHistoryItem.QUERY_ALL_ROWS);
    }
}

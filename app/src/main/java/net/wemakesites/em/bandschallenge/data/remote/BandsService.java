package net.wemakesites.em.bandschallenge.data.remote;


import net.wemakesites.em.bandschallenge.data.model.response.bandDetails.BandDetailsResponse;
import net.wemakesites.em.bandschallenge.data.model.response.search.SearchResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BandsService {

    @GET("search/band_name/{keyword}")
    Observable<SearchResponse> searchBands(@Path("keyword") String keyword);

    @GET("band/{band_id}")
    Observable<BandDetailsResponse> getBand(@Path("band_id") String bandId);
}

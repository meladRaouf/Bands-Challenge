package net.wemakesites.em.bandschallenge.data.model.response.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResponseData {

    @SerializedName("search_results")
    @Expose
    private List<SearchResult> searchResults;

    public List<SearchResult> getSearchResults() {
        return searchResults;
    }


}
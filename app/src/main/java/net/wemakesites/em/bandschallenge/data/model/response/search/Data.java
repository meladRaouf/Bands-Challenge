package net.wemakesites.em.bandschallenge.data.model.response.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Data {

    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("search_results")
    @Expose
    private ArrayList<SearchResult> searchResults = null;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public ArrayList<SearchResult> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(ArrayList<SearchResult> searchResults) {
        this.searchResults = searchResults;
    }

}

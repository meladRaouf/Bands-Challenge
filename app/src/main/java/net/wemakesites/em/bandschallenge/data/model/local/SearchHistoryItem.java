package net.wemakesites.em.bandschallenge.data.model.local;


import android.content.ContentValues;
import android.database.Cursor;

import net.wemakesites.em.bandschallenge.data.model.response.search.SearchResult;
import net.wemakesites.em.bandschallenge.utils.DbUtil;

import io.reactivex.functions.Function;


public class SearchHistoryItem {
    public static final String TABLE = "bands_history";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String QUERY_ALL_ROWS = "select * from " + TABLE;
    public static final Function<Cursor, SearchHistoryItem> MAPPER = cursor -> {
        final long id = DbUtil.getLong(cursor, ID);
        final String name = DbUtil.getString(cursor, NAME);
        return new SearchHistoryItem(id, name);
    };
    private long id;
    private String name;

    private SearchHistoryItem(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static ContentValues convertToLocalItem(final SearchResult searchResult) {
        final ContentValues values = new ContentValues();
        values.put(ID, searchResult.getId());
        values.put(NAME, searchResult.getName());

        return values;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}

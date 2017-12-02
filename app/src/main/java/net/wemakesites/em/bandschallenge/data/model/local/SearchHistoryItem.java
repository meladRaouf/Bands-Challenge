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

    private long id;
    private String name;

    public SearchHistoryItem(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Function<Cursor, SearchHistoryItem> MAPPER = new Function<Cursor, SearchHistoryItem>() {
        @Override
        public SearchHistoryItem apply(Cursor cursor) {
            long id = DbUtil.getLong(cursor, ID);
            String name = DbUtil.getString(cursor, NAME);
            return new SearchHistoryItem(id, name);
        }
    };


    public static ContentValues convertToLocalItem(SearchResult searchResult) {
        ContentValues values = new ContentValues();
        values.put(ID, searchResult.getId());
        values.put(NAME, searchResult.getName());

        return values;
    }
}

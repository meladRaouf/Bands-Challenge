package net.wemakesites.em.bandschallenge.data.local;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;

import net.wemakesites.em.bandschallenge.data.model.local.SearchHistoryItem;


public class DbCallback extends SupportSQLiteOpenHelper.Callback {
    private static final int VERSION = 1;

    private static final String CREATE_SEARCH_LIST =
            "CREATE TABLE " + SearchHistoryItem.TABLE + "("
                    + SearchHistoryItem.ID + " INTEGER NOT NULL PRIMARY KEY,"
                    + SearchHistoryItem.NAME + " TEXT NOT NULL"
                    + ")";

    public DbCallback() {
        super(VERSION);
    }

    @Override
    public void onCreate(final SupportSQLiteDatabase db) {
        db.execSQL(CREATE_SEARCH_LIST);

    }

    @Override
    public void onUpgrade(final SupportSQLiteDatabase db, final int oldVersion, final int newVersion) {
        //We don't need to do anything in database upgrade.
    }
}

package net.wemakesites.em.bandschallenge.utils;

import android.database.Cursor;

public final class DbUtil {
    private DbUtil() {

    }

    public static String getString(final Cursor cursor, final String columnName) {
        return cursor.getString(cursor.getColumnIndexOrThrow(columnName));
    }

    public static long getLong(final Cursor cursor, final String columnName) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(columnName));
    }
}

package net.wemakesites.em.bandschallenge.data.model.local;


import android.content.ContentValues;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class SearchHistoryItem {
    public static final String TABLE = "bands";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public abstract long id();
    public abstract String name();

    public static final class Builder {
        private final ContentValues values = new ContentValues();

        public Builder id(long id) {
            values.put(ID, id);
            return this;
        }

        public Builder name(String name) {
            values.put(NAME, name);
            return this;
        }



        public ContentValues build() {
            return values;
        }
    }
}

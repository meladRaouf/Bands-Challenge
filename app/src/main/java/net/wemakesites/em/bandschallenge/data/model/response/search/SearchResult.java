package net.wemakesites.em.bandschallenge.data.model.response.search;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class SearchResult implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SearchResult> CREATOR = new Parcelable.Creator<SearchResult>() {
        @Override
        public SearchResult createFromParcel(final Parcel parcel) {
            return new SearchResult(parcel);
        }

        @Override
        public SearchResult[] newArray(final int size) {
            return new SearchResult[size];
        }
    };
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("genre")
    @Expose
    private String genre;
    @SerializedName("country")
    @Expose
    private String country;

    protected SearchResult(final Parcel in) {
        name = in.readString();
        id = in.readLong();
        genre = in.readString();
        country = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(name);
        dest.writeLong(id);
        dest.writeString(genre);
        dest.writeString(country);
    }
}
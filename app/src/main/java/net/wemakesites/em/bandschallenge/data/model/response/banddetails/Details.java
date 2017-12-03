package net.wemakesites.em.bandschallenge.data.model.response.banddetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("country of origin")
    @Expose
    private String countryOfOrigin;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("formed in")
    @Expose
    private String formedIn;
    @SerializedName("genre")
    @Expose
    private String genre;
    @SerializedName("lyrical themes")
    @Expose
    private String lyricalThemes;
    @SerializedName("current label")
    @Expose
    private String currentLabel;
    @SerializedName("years active")
    @Expose
    private String yearsActive;

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(final String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getFormedIn() {
        return formedIn;
    }

    public void setFormedIn(final String formedIn) {
        this.formedIn = formedIn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

    public String getLyricalThemes() {
        return lyricalThemes;
    }

    public void setLyricalThemes(final String lyricalThemes) {
        this.lyricalThemes = lyricalThemes;
    }

    public String getCurrentLabel() {
        return currentLabel;
    }

    public void setCurrentLabel(final String currentLabel) {
        this.currentLabel = currentLabel;
    }

    public String getYearsActive() {
        return yearsActive;
    }

    public void setYearsActive(final String yearsActive) {
        this.yearsActive = yearsActive;
    }

}

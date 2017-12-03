
package net.wemakesites.em.bandschallenge.data.model.response.banddetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BandData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("details")
    @Expose
    private Details details;
    @SerializedName("band_name")
    @Expose
    private String bandName;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("discography")
    @Expose
    private List<Discography> discography;
    @SerializedName("current_lineup")
    @Expose
    private List<CurrentLineup> currentLineup;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(final Details details) {
        this.details = details;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(final String bandName) {
        this.bandName = bandName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(final String logo) {
        this.logo = logo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(final String photo) {
        this.photo = photo;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(final String bio) {
        this.bio = bio;
    }

    public List<Discography> getDiscography() {
        return discography;
    }

    public void setDiscography(final List<Discography> discography) {
        this.discography = discography;
    }

    public List<CurrentLineup> getCurrentLineup() {
        return currentLineup;
    }

    public void setCurrentLineup(final List<CurrentLineup> currentLineup) {
        this.currentLineup = currentLineup;
    }

}

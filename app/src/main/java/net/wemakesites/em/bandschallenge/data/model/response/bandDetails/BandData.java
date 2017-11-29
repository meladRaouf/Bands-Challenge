
package net.wemakesites.em.bandschallenge.data.model.response.bandDetails;

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
    private List<Discography> discography = null;
    @SerializedName("current_lineup")
    @Expose
    private List<CurrentLineup> currentLineup = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Discography> getDiscography() {
        return discography;
    }

    public void setDiscography(List<Discography> discography) {
        this.discography = discography;
    }

    public List<CurrentLineup> getCurrentLineup() {
        return currentLineup;
    }

    public void setCurrentLineup(List<CurrentLineup> currentLineup) {
        this.currentLineup = currentLineup;
    }

}

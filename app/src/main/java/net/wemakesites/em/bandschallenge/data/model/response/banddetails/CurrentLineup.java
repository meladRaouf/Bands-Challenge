package net.wemakesites.em.bandschallenge.data.model.response.banddetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentLineup {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("instrument")
    @Expose
    private String instrument;
    @SerializedName("years")
    @Expose
    private String years;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(final String instrument) {
        this.instrument = instrument;
    }

    public String getYears() {
        return years;
    }

    public void setYears(final String years) {
        this.years = years;
    }

}

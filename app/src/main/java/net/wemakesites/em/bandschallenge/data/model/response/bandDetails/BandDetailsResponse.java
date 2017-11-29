package net.wemakesites.em.bandschallenge.data.model.response.bandDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BandDetailsResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("bandData")
    @Expose
    private BandData bandData;
    @SerializedName("hash")
    @Expose
    private String hash;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BandData getBandData() {
        return bandData;
    }

    public void setBandData(BandData bandData) {
        this.bandData = bandData;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

}

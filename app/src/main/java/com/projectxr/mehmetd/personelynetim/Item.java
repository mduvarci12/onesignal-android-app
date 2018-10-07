package com.projectxr.mehmetd.personelynetim;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.projectxr.mehmetd.personelynetim.ItemResponse;


public class Item {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("firmalar")
    @Expose
    private List<ItemResponse> firmalar = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Item() {
    }

    /**
     * @param status
     * @param firmalar
     */
    public Item(String status, List<ItemResponse> firmalar) {
        super();
        this.status = status;
        this.firmalar = firmalar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ItemResponse> getFirmalar() {
        return firmalar;
    }

    public void setFirmalar(List<ItemResponse> firmalar) {
        this.firmalar = firmalar;
    }


}

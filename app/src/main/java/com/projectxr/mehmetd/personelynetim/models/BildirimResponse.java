package com.projectxr.mehmetd.personelynetim.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BildirimResponse implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("bildirimler")
    @Expose
    private List<Bildirimler> bildirimler = null;
    private final static long serialVersionUID = 3548500618987725376L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BildirimResponse withStatus(String status) {
        this.status = status;
        return this;
    }

    public List<Bildirimler> getBildirimler() {
        return bildirimler;
    }

    public void setBildirimler(List<Bildirimler> bildirimler) {
        this.bildirimler = bildirimler;
    }

    public BildirimResponse withBildirimler(List<Bildirimler> bildirimler) {
        this.bildirimler = bildirimler;
        return this;
    }

}
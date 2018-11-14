package com.projectxr.mehmetd.personelynetim.models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bildirimler implements Serializable
{
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("musteri")
    @Expose
    private String musteri;
    @SerializedName("calisan")
    @Expose
    private String calisan;
    @SerializedName("bildirim_id")
    @Expose
    private String bildirimId;
    @SerializedName("musteriler")
    @Expose
    private String musteriler;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @SerializedName("status")
    @Expose



    private Integer status;
    private final static long serialVersionUID = 8360944626846784812L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bildirimler withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getMusteri() {
        return musteri;
    }

    public void setMusteri(String musteri) {
        this.musteri = musteri;
    }

    public Bildirimler withMusteri(String musteri) {
        this.musteri = musteri;
        return this;
    }

    public String getCalisan() {
        return calisan;
    }

    public void setCalisan(String calisan) {
        this.calisan = calisan;
    }

    public Bildirimler withCalisan(String calisan) {
        this.calisan = calisan;
        return this;
    }

    public String getBildirimId() {
        return bildirimId;
    }

    public void setBildirimId(String bildirimId) {
        this.bildirimId = bildirimId;
    }

    public Bildirimler withBildirimId(String bildirimId) {
        this.bildirimId = bildirimId;
        return this;
    }

    public String getMusteriler() {
        return musteriler;
    }

    public void setMusteriler(String musteriler) {
        this.musteriler = musteriler;
    }

    public Bildirimler withMusteriler(String musteriler) {
        this.musteriler = musteriler;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Bildirimler withStatus(Integer status) {
        this.status = status;
        return this;
    }

}
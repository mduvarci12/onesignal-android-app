package com.projectxr.mehmetd.personelynetim;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("mekan_id")
    @Expose
    private String mekanId;
    @SerializedName("mekan_foto")
    @Expose
    private String mekanFoto;

    /**
     * No args constructor for use in serialization
     *
     */
    public ItemResponse() {
    }

    /**
     *

     * @param id
     * @param mekanFoto
     * @param title

     * @param userId
     * @param mekanId
     */
    public ItemResponse(Integer id, String title, String mekanId, String mekanFoto, String userId, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.title = title;
        this.mekanId = mekanId;
        this.mekanFoto = mekanFoto;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMekanId() {
        return mekanId;
    }

    public void setMekanId(String mekanId) {
        this.mekanId = mekanId;
    }

    public String getMekanFoto() {
        return mekanFoto;
    }

    public void setMekanFoto(String mekanFoto) {
        this.mekanFoto = mekanFoto;
    }





}
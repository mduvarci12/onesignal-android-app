package com.projectxr.mehmetd.personelynetim.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class firma_id {
    @SerializedName("firma_id")
    @Expose
    private List<firma_id> firma_id = null;

    public firma_id(List<com.projectxr.mehmetd.personelynetim.models.firma_id> firma_id) {
        this.firma_id = firma_id;
    }

    public List<com.projectxr.mehmetd.personelynetim.models.firma_id> getFirma_id() {
        return firma_id;
    }
}

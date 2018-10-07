package com.projectxr.mehmetd.personelynetim.models;

public class playerId {
    private String message;
    private String status;

    public playerId(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}

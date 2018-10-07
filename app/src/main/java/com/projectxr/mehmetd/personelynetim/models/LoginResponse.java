package com.projectxr.mehmetd.personelynetim.models;

public class LoginResponse {

  private String status;
  private String key;

    public LoginResponse(String status, String key) {
        this.status = status;
        this.key = key;
    }

    public String getStatus() {
        return status;
    }

    public String getKey() {
        return key;
    }
}

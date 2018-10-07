package com.projectxr.mehmetd.personelynetim.models;

public class User {
    // oturum açılınca apiden gelecek JSON modeli
    private  int id;
    private String name;
// JSON modeli constructeri
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
// Getter fonksiyonları
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

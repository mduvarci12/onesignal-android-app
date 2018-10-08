package com.projectxr.mehmetd.personelynetim.models;

import java.util.ArrayList;
import java.util.List;

public class departmanModel {
    private List<String>  postDepartman;

    public departmanModel(List<String> postDepartman) {
        this.postDepartman = postDepartman;
    }

    public List<String> getPostDepartman() {
        return postDepartman;
    }
}

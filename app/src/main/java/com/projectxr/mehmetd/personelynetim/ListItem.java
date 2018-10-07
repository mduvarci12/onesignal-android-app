package com.projectxr.mehmetd.personelynetim;

public class ListItem {

    private String productImage;
    private String firmaName;
    private String mekan_id;

    public ListItem(String productImage, String firmaName, String mekan_id) {
        this.productImage = productImage;
        this.firmaName = firmaName;
        this.mekan_id = mekan_id;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getFirmaName() {
        return firmaName;
    }


    public String getMekan_id() {
        return mekan_id;
    }
}

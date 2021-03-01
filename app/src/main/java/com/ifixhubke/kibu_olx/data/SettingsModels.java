package com.ifixhubke.kibu_olx.data;

public class SettingsModels {
    private String name ;
    private String price;
    private String image;

    public SettingsModels(String name, String price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public SettingsModels() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

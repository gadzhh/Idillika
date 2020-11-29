package com.example.idillika.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Store {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("imageLink")
    @Expose
    private String imageLink;

    @SerializedName("brand")
    @Expose
    private String name;

    @SerializedName("title")
    @Expose
    private String description;

    @SerializedName("price")
    @Expose
    private int price;

    private boolean isFavorite;

    public int getId() {
        return id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getBrand() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}

package com.example.samsung.gp.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by TOSHIBA on 2016-04-07.
 */
public class KhrogaPackage implements Serializable{

    private ArrayList<KhrogaItem> KhrogaPackageList;
    private String title;
    private String mixImage;
    private String price;
    private String rating;
    private boolean favorited;


    public ArrayList<KhrogaItem> getKhrogaPackage() {
        return KhrogaPackageList;
    }

    public void setKhrogaPackage(ArrayList<KhrogaItem> khrogaPackageList) { // de hb2a static ghlbn lw etl5bt
        KhrogaPackageList = khrogaPackageList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMixImage() {
        return mixImage;
    }

    public void setMixImage(String mixImage) {
        this.mixImage = mixImage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

}

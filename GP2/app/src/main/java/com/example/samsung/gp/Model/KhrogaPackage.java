package com.example.samsung.gp.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by TOSHIBA on 2016-04-07.
 */
public class KhrogaPackage implements Serializable ,Comparable<KhrogaPackage>{

    private ArrayList<KhrogaItem> KhrogaPackageList;
    private String ID;
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


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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


    public String shareThatPackage() {
        //// TODO: 2016-06-17 : change this AppName
        String APPNAME="Download YALLA from playstore *InsertAppPlayStoreLinkHere*";

        String strText="Yalla " ;
        for (int i=0 ; i< KhrogaPackageList.size();i++){

             strText+=  KhrogaPackageList.get(i).getName();
            if(i!=KhrogaPackageList.size()-1)
                strText+=" & ";

        }
        strText+="\nE3ml 7esabk 3la "+this.getPrice()+" LE\n\n"+APPNAME;



        return strText;


    }


    @Override
    public int compareTo(KhrogaPackage another) {
        int compareQuantity =Integer.parseInt(another.getPrice());
        //descending order
        return compareQuantity - Integer.parseInt(this.price);

    }
}

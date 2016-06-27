package com.example.samsung.gp.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by TOSHIBA on 2016-04-07.
 */
public class KhrogaItem implements Serializable{

     private String ID;
     private String name;
     private String price;
     private String category;
     private String address;
     private String rate;
     private String openHours;
     private String cuisine ;
     private String features;
     private String phone;
     private String image;
     private String location;
     private String banner;

     public KhrogaItem(JSONObject jItem) {
          try {
               this.ID = jItem.getString("ItemId");
               this.name = jItem.getString("ItemName");
               this.price = jItem.getString("ItemPrice");
               this.category =jItem.getString("CategoryName");
               this.address = jItem.getString("ItemAddress");
               this.rate = jItem.getString("ItemRating");
               this.openHours = jItem.getString("OpenHours");
               this.cuisine = jItem.getString("Cuisine");
               this.features =jItem.getString("Features");
               this.phone = jItem.getString("Phone");
               this.image =jItem.getString("ItemImage");
               this.location = jItem.getString("ItemLocation");
               this.banner = jItem.getString("BannerImage");
          } catch (JSONException e) {
               e.printStackTrace();
          }

     }

     public KhrogaItem(String ID, String name, String price, String category, String address, String rate, String openHours, String cuisine, String features, String phone, String image, String location) {
          this.ID = ID;
          this.name = name;
          this.price = price;
          this.category = category;
          this.address = address;
          this.rate = rate;
          this.openHours = openHours;
          this.cuisine = cuisine;
          this.features = features;
          this.phone = phone;
          this.image = image;
          this.location = location;
     }


     public KhrogaItem(String ID, String name, String price, String category, String address, String rate, String openHours, String cuisine, String features, String phone, String image, String location,String banner) {
          this.ID = ID;
          this.name = name;
          this.price = price;
          this.category = category;
          this.address = address;
          this.rate = rate;
          this.openHours = openHours;
          this.cuisine = cuisine;
          this.features = features;
          this.phone = phone;
          this.image = image;
          this.location = location;
          this.banner = banner;
     }

     public String getID() {
          return ID;
     }

     public KhrogaItem() {

     }

     public void setID(String ID) {
          this.ID = ID;
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

     public String getCategory() {
          return category;
     }

     public void setCategory(String category) {
          this.category = category;
     }

     public String getAddress() {
          return address;
     }

     public void setAddress(String address) {
          this.address = address;
     }

     public String getRate() {
          return rate;
     }

     public void setRate(String rate) {
          this.rate = rate;
     }

     public String getOpenHours() {
          return openHours;
     }

     public void setOpenHours(String openHours) {
          this.openHours = openHours;
     }

     public String getCuisine() {
          return cuisine;
     }

     public void setCuisine(String cuisine) {
          this.cuisine = cuisine;
     }

     public String getFeatures() {
          return features;
     }

     public void setFeatures(String features) {
          this.features = features;
     }

     public String getPhone() {
          return phone;
     }

     public void setPhone(String phone) {
          this.phone = phone;
     }

     public String getImage() {
          return image;
     }

     public void setImage(String image) {
          this.image = image;
     }

     public String getLocation() {
          return location;
     }

     public void setLocation(String location) {
          this.location = location;
     }

     public String getBanner() {
          return banner;
     }

     public void setBanner(String banner) {
          this.banner = banner;
     }



}

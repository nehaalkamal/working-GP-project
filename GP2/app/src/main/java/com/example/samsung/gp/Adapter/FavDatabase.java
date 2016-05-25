package com.example.samsung.gp.Adapter;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.samsung.gp.Model.KhrogaPackage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by TOSHIBA on 2016-01-11.
 */
public class FavDatabase {
    public static void saveFKhrogaPackagesList(ArrayList<KhrogaPackage> FavKhrogaPackage,Context context){
        String jsonKhrogaPackageList = new Gson().toJson(FavKhrogaPackage);
        SharedPreferences sharedPref = context.getSharedPreferences("FavKhrogaPackages_db", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("FavouriteKhrogaPackages", jsonKhrogaPackageList);
        editor.commit();

    }



    public static ArrayList<KhrogaPackage> getFKhrogaPackagesList(Context context){
        Gson gson = new Gson();
        ArrayList<KhrogaPackage> KhrogaPackagesFromShared;
        SharedPreferences sharedPref = context.getSharedPreferences("FavKhrogaPackages_db", Context.MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString("FavouriteKhrogaPackages", "");

        Type type = new TypeToken<ArrayList<KhrogaPackage>>() {}.getType();
        KhrogaPackagesFromShared = gson.fromJson(jsonPreferences, type);

        return KhrogaPackagesFromShared;
    }

    public static void clearFKhrogaPackage(KhrogaPackage MyKhrogaPackage,Context context){
        Gson gson = new Gson();
        ArrayList<KhrogaPackage> KhrogaPackagesFromShared = new ArrayList<>();
        SharedPreferences sharedPref = context.getSharedPreferences("FavKhrogaPackages_db", Context.MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString("FavouriteKhrogaPackages", "");

        Type type = new TypeToken<ArrayList<KhrogaPackage>>() {}.getType();
        KhrogaPackagesFromShared = gson.fromJson(jsonPreferences, type);
        for(int i=0;i<KhrogaPackagesFromShared.size();i++){
            if(MyKhrogaPackage.getTitle().equals(KhrogaPackagesFromShared.get(i).getTitle())){
                KhrogaPackagesFromShared.remove(i);
                break;
            }
        }
        String jsonKhrogaPackageList = new Gson().toJson(KhrogaPackagesFromShared);

        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("FavouriteKhrogaPackages", jsonKhrogaPackageList);
        editor.commit();

    }

}

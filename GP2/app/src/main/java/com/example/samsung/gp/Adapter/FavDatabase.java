package com.example.samsung.gp.Adapter;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.samsung.gp.Model.KhrogaPackage;
import com.example.samsung.gp.app.AppConfig;
import com.example.samsung.gp.app.AppController;
import com.example.samsung.gp.helper.SQLiteHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TOSHIBA on 2016-01-11.
 */
public class FavDatabase {
    static SQLiteHandler db;// = new SQLiteHandler(context);
    // Fetching user details from SQLite
    static HashMap<String, String> user;

 private static String getUserEmail(Context context){

    db = new SQLiteHandler(context);
    user = db.getUserDetails();
    String email = user.get("email");
    return email;

}


    public static void initiateSPfromBE(String jsonKhrogaPackageList,Context context){ //update shared pref from backend
        SharedPreferences sharedPref = context.getSharedPreferences("FavKhrogaPackages_db", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getUserEmail(context), jsonKhrogaPackageList);
        editor.apply();

    }
    public static void updateBEfromSP(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("FavKhrogaPackages_db", Context.MODE_PRIVATE);
        String jsonFavs = sharedPref.getString(getUserEmail(context), "");
        updateBE( jsonFavs, context); //private function

    }

    private  static void updateBE(final String jsonFavs, final Context context) {


        // Tag used to cancel the request
        String tag_string_req = "req_update_favs";



        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_Update_Fav, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        Toast.makeText(context, jObj.getString("response_msg"), Toast.LENGTH_LONG).show();
                    } else {

                        // Error occurred in update. Get the error
                        // message
                        String errorMsg = jObj.getString("response_msg");
                        Toast.makeText(context,
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("FavDatabase", "Update favs Error: " + error.getMessage());
                Toast.makeText(context,
                        error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", getUserEmail(context));
                params.put("favourites", jsonFavs);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    public static void saveFKhrogaPackagesList(ArrayList<KhrogaPackage> FavKhrogaPackage,Context context){
        String jsonKhrogaPackageList = new Gson().toJson(FavKhrogaPackage);
        SharedPreferences sharedPref = context.getSharedPreferences("FavKhrogaPackages_db", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getUserEmail(context), jsonKhrogaPackageList);
        editor.commit();

    }
    public static void saveSingleKhrogaPackage(KhrogaPackage FavKhrogaPackage,Context context){

        ArrayList<KhrogaPackage> list = new ArrayList<>();
        if(getFKhrogaPackagesList(context)!=null){
        list = getFKhrogaPackagesList(context);}
        list.add(FavKhrogaPackage);
        String jsonKhrogaPackageList = new Gson().toJson(list);
        SharedPreferences sharedPref = context.getSharedPreferences("FavKhrogaPackages_db", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString( getUserEmail(context), jsonKhrogaPackageList);
        editor.commit();

    }



    public static ArrayList<KhrogaPackage> getFKhrogaPackagesList(Context context){ //no shared, check BE
        Gson gson = new Gson();
        ArrayList<KhrogaPackage> KhrogaPackagesFromShared=new ArrayList<>();
        SharedPreferences sharedPref = context.getSharedPreferences("FavKhrogaPackages_db", Context.MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString(getUserEmail(context), "");

       /* if(jsonPreferences.equals("")){ // hasheelha
            //check BE an equal it with jsonPrefe
        }*/

        Type type = new TypeToken<ArrayList<KhrogaPackage>>() {}.getType();
        KhrogaPackagesFromShared = gson.fromJson(jsonPreferences, type);

        return KhrogaPackagesFromShared;
    }

    public static void unsaveSingleKhrogaPackage(KhrogaPackage MyKhrogaPackage,Context context){
        Gson gson = new Gson();
        ArrayList<KhrogaPackage> KhrogaPackagesFromShared = new ArrayList<>();
        SharedPreferences sharedPref = context.getSharedPreferences("FavKhrogaPackages_db", Context.MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString(getUserEmail(context), "");

        Type type = new TypeToken<ArrayList<KhrogaPackage>>() {}.getType();
        KhrogaPackagesFromShared = gson.fromJson(jsonPreferences, type);

        if(KhrogaPackagesFromShared == null)
            return;
        for(int i=0;i<KhrogaPackagesFromShared.size();i++){
            if(KhrogaPackagesFromShared.get(i).getID().equals(MyKhrogaPackage.getID())){
                KhrogaPackagesFromShared.remove(i);
                break;
            }
        }
        String jsonKhrogaPackageList = new Gson().toJson(KhrogaPackagesFromShared);

        SharedPreferences.Editor editor = sharedPref.edit();
        if(KhrogaPackagesFromShared.size()!=0)
            editor.putString(getUserEmail(context), jsonKhrogaPackageList);
        else
            editor.putString(getUserEmail(context),"");

        editor.commit();

    }

    public static void clearMyFav(Context context){
    SharedPreferences sharedPref = context.getSharedPreferences("FavKhrogaPackages_db", Context.MODE_PRIVATE);

    //sharedPref.edit().remove(getName(context)).apply();

    SharedPreferences.Editor editor = sharedPref.edit();

    editor.putString(getUserEmail(context),"").apply();


    }


}

package com.example.samsung.gp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.samsung.gp.Adapter.FeaturedAdapter;
import com.example.samsung.gp.Model.KhrogaItem;
import com.example.samsung.gp.app.AppConfig;
import com.example.samsung.gp.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeaturedFragment extends Fragment {
    private FeaturedAdapter featuredAdapter;
    private ListView featuredListView;
    private static ArrayList <KhrogaItem> ItemList = new ArrayList<>();


    public FeaturedFragment() {
        // Required empty public constructor
    }



    @Override
    public void onStart() {
        super.onStart();
        getFeaturedService(); //to add location

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_featured, container, false);

/*
        myItemList.add(new KhrogaItem("148","Starbucks","50","Maadi Image Centre, Corniche El Nil", "3", "8","7:00 am to 12:00 am",
                "Coffee Shop", "Outdoor, Nile/Sea View","02 25280494","http://i.imgur.com/qTHnZAk.png","Maadi","http://i.imgur.com/f0LyTYh.png?1"
        ));
        myItemList.add(new KhrogaItem("148","Mcdonalds","50","Maadi Image Centre, Corniche El Nil", "3", "8","7:00 am to 12:00 am",
                "Coffee Shop", "Outdoor, Nile/Sea View","02 25280494","http://i.imgur.com/qTHnZAk.png","Maadi","http://i.imgur.com/JkTrxVe.jpg?1"
        ));
*/


        featuredAdapter = new FeaturedAdapter(getActivity(), ItemList); //list of khrogaitems
        featuredListView = (ListView) rootView.findViewById(R.id.featured_list);
        featuredListView.setAdapter(featuredAdapter);

        featuredListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent DetailIntent = new Intent(getActivity(), item_details.class);
                DetailIntent.putExtra("DetailedItem", featuredAdapter.getItem(position));
                startActivity(DetailIntent);

            }
        });

        return rootView;
    }



    private void getFeaturedService(){

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                AppConfig.URL_Featured_Items, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                // Log.d("json", response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    for(int i= 0; i<response.getJSONArray("ItemsResponse").length() ;i++){
                        ItemList.add( new KhrogaItem(  (JSONObject)response.getJSONArray("ItemsResponse").get(i)) );
                        Log.e("testFeaturedFragment", "" + ItemList.size());
                    }

                    featuredAdapter.notifyDataSetChanged();
                    featuredListView.setAdapter(featuredAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(),
                            "JsonError: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                Toast.makeText(getContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                // hide the progress dialog

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);




    }



    @Override
    public void onStop() {
        super.onStop();
        ItemList.clear();
    }
}

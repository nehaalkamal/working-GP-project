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
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.samsung.gp.Adapter.listingAdapter;
import com.example.samsung.gp.Adapter.spinnerAdapter;
import com.example.samsung.gp.Model.KhrogaItem;
import com.example.samsung.gp.app.AppConfig;
import com.example.samsung.gp.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    /*private int mPosition = GridView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";
*/

    private listingAdapter listing_Adapter;
    private ListView searchListView;
    private static ArrayList<KhrogaItem> ItemList  = new ArrayList<>();

    private static ArrayList<KhrogaItem> AllFullItemList  = new ArrayList<>();


    public SearchFragment() {
        // Required empty public constructor
    }


//    Parcelable state;

/*
    @Override
    public void onPause() {
        // Save ListView state @ onPause
        Log.d("TAG", "saving listview state @ onPause");
        state = searchListView.onSaveInstanceState();
        super.onPause();
    }
*/


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSearchService();
        Log.e("search", "started");
    }

    //TODO: OnStart or OnCreate is better for NetworkCall wise
/*
    @Override
    public void onStart() {
        super.onStart();
        getSearchService();
        Log.e("search", "started");
*/
        /*if (state != null) {
            Log.d("TAG", "trying to restore listview state..");
            searchListView.onRestoreInstanceState(state);
        }*/


//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_search, container, false);


        addItemsToSpinner();

        listing_Adapter = new listingAdapter(getActivity(),ItemList); //list of khrogaitems
        searchListView = (ListView) rootView.findViewById(R.id.search_list);
        searchListView.setAdapter(listing_Adapter);

        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent DetailIntent = new Intent(getActivity(), item_details.class);
                DetailIntent.putExtra("DetailedItem", listing_Adapter.getItem(position));
                startActivity(DetailIntent);

            }
        });

        return  rootView;
    }



    private void  getSearchService(){

         JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                AppConfig.URL_Search_Items, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                // Log.d("json", response.toString());

                try {

                    for(int i= 0; i<response.getJSONArray("ItemsResponse").length() ;i++){
                        ItemList.add( new KhrogaItem(  (JSONObject)response.getJSONArray("ItemsResponse").get(i)) );
                        AllFullItemList.add( new KhrogaItem(  (JSONObject)response.getJSONArray("ItemsResponse").get(i)) );
                        Log.e("testSearchFragment", "" + ItemList.size());
                    }

                    listing_Adapter.notifyDataSetChanged();
                    searchListView.setAdapter(listing_Adapter);
                    /*if (state != null) {
                        Log.d("TAG", "trying to restore listview state..");
                        searchListView.onRestoreInstanceState(state);
                    }*/



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
    public void onDestroy() {
        super.onDestroy();
        Log.e("search", "stopped");
        ItemList.clear();
        AllFullItemList.clear();

    }

    /*@Override
    public void onStop() {
        *//*Log.d("TAG", "saving listview state @ onPause");
        state = searchListView.onSaveInstanceState();
*//*
        super.onStop();
        Log.e("search", "stopped");
        ItemList.clear();
        AllFullItemList.clear();

    }*/

    public void addItemsToSpinner() {
/*
        ArrayList<String> list = new ArrayList<String>();
        list.add("All");
        list.add("Maadi");
        list.add("Nasr city");
        list.add("Choose Area");*/



        // Custom ArrayAdapter with spinner item layout to set popup background

        spinnerAdapter spinAdapter = new spinnerAdapter(getContext(), R.layout.spinner_item);
        spinAdapter.add("All Areas");
        spinAdapter.addAll(getResources().getStringArray(R.array.array_spinner_areas));



        // Default ArrayAdapter with default spinner item layout, getting some
        // view rendering problem in lollypop device, need to test in other
        // devices

  /*
   * ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(this,
   * android.R.layout.simple_spinner_item, list);
   * spinAdapter.setDropDownViewResource
   * (android.R.layout.simple_spinner_dropdown_item);
   */
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinner_nav);

        spinner.setAdapter(spinAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item

                String item = adapter.getItemAtPosition(position).toString().replaceAll("\\s", "");
                if (!item.equals("AllAreas")) {
                    ItemList.clear();

                    for (KhrogaItem khItem : AllFullItemList) {
                        if (khItem.getLocation().equals(item)) {
                            ItemList.add(khItem);
                        }
                    }

                    listing_Adapter.notifyDataSetChanged();

                } else { //user chose "All Areas"

                    if (!AllFullItemList.isEmpty()) {
                        ItemList.clear();
                        ItemList.addAll(AllFullItemList); //list of khrogaitems
                        listing_Adapter.notifyDataSetChanged();
                    }
                }


             }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }


}

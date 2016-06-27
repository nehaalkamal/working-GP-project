package com.example.samsung.gp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.samsung.gp.Adapter.spinnerAdapter;
import com.example.samsung.gp.Model.KhrogaItem;
import com.example.samsung.gp.app.AppConfig;
import com.example.samsung.gp.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class core_screenFragment extends Fragment {
    Spinner spinner;
    //String[] spinnerList = {"Maadi","Nasr City"}; added in string.xml
    private EditText budget;
    private CheckBox foodButton;
    private CheckBox cafeButton;
    private CheckBox cinemaButton;
    private CheckBox entertainmenttButton;
    private CheckBox ahwaButton;
    private CheckBox dessertButton;
    private Dialog list_dialog;
    private Button yallaButton;

    private static ArrayList<KhrogaItem> myItemList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_core_screen, container, false);


        budget = (EditText) rootView.findViewById(R.id.budgetvalue);
        foodButton = (CheckBox) rootView.findViewById(R.id.food);
        cafeButton = (CheckBox) rootView.findViewById(R.id.cafe);
        entertainmenttButton = (CheckBox) rootView.findViewById(R.id.game);
        cinemaButton = (CheckBox) rootView.findViewById(R.id.cinema);
        ahwaButton = (CheckBox) rootView.findViewById(R.id.ahwa);
        dessertButton = (CheckBox) rootView.findViewById(R.id.dessert);
        yallaButton = (Button) rootView.findViewById(R.id.YallaButton);
        spinner= (Spinner) rootView.findViewById(R.id.spinner1);



/*
        Spinner spinner = (Spinner) rootView.findViewById(R.id.AreaSpinner);
        ArrayAdapter<CharSequence> Spinneradapter = ArrayAdapter.createFromResource(context, R.array.AreasArray, android.R.layout.simple_spinner_dropdown_item);

        Spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long id) {

                spinnerValue = arg0.getItemAtPosition(pos)
                        .toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {


            }
        });

        spinner.setAdapter(Spinneradapter);
        */



        spinnerAdapter adapter = new spinnerAdapter(getContext(), android.R.layout.simple_list_item_1);
        adapter.addAll(getResources().getStringArray(R.array.array_spinner_areas));

        //adapter.addAll(spinnerList);
        //adapter.add("Choose Area");
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


       /* // Fetching user details from SQLite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        String email = user.get("email");

        // Displaying the user details on the screen
        txtName.setText(name);
        txtEmail.setText(email);*/


        yallaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String budgetValue = budget.getText().toString().trim();
                String spinnerValue = spinner.getSelectedItem().toString().replaceAll("\\s", ""); //<-to remove the space between nasr city

                if (Integer.parseInt(budgetValue) < 15) { //3ayz ytzbt
                    showDialogue();
                }
                else{
                    // Check for empty data in the correct form

                    if (!budgetValue.isEmpty() && !spinnerValue.equals(getResources().getString(R.string.choose_area).replaceAll("\\s", "")) && !getCheckedButtons().isEmpty()) {


                    String bracketedChoices = "(";

                    for (int i = 0; i < getCheckedButtons().size(); i++) {
                        bracketedChoices += "\'" + getCheckedButtons().get(i) + "\'";

                        if (i != (getCheckedButtons().size() - 1))
                            bracketedChoices += ",";
                    }
                    bracketedChoices += ")";

                    Log.e("TestbracketedChoices", bracketedChoices);

                    getItemsParamterized(budgetValue, spinnerValue, bracketedChoices);

                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getContext(),
                            "Lazm ted5al kol el matloob", Toast.LENGTH_LONG)
                            .show();
                }

            }


            }

        });

        return rootView;
    }



    private void getItemsParamterized(final String ItemPrice, final String ItemLocation , final String CategoryName) {

        //RequestQueue queue = Volley.newRequestQueue(getContext());
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());


        JsonObjectRequest strReq = new JsonObjectRequest(Request.Method.GET,
                AppConfig.URL_Items_parameterized+"?ItemPrice="+ItemPrice+"&ItemLocation="+ItemLocation+"&CategoryName="+CategoryName, null,new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {

                    for(int i= 0; i<response.getJSONArray("ItemsResponse").length() ;i++){
                        myItemList.add(new KhrogaItem((JSONObject) response.getJSONArray("ItemsResponse").get(i)));

                    }
                    Log.e("testSuccessWebservice", "" + myItemList.size());

                    sendIntent(myItemList);
                    progressDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();

                    Log.e("CoreScreenFragment", e.getMessage());
                    progressDialog.dismiss();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley error", "Error: " + error.getMessage());
                Toast.makeText(getContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                progressDialog.dismiss();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq);

        //initialize the progress dialog and show it
        progressDialog.setMessage("Making Khrogat...");
        progressDialog.show();

    }

    private void sendIntent(ArrayList<KhrogaItem> ItemList) {
        Intent intent = new Intent(getActivity(), KhrogatPackages.class);
        intent.putExtra("myListItem", ItemList);
        intent.putExtra("budget", budget.getText().toString().trim()); //zawd dah w est2blo henak
        getActivity().startActivity(intent);
        myItemList.clear();
    }

    ArrayList<String> getCheckedButtons() {
        ArrayList<String> Choices = new ArrayList<>();
        if (foodButton.isChecked()) {
            Choices.add("restaurant");
        }

        if (cafeButton.isChecked() && foodButton.isChecked()) {
            Choices.add("Cafe_Restaurant");
        }

        if (entertainmenttButton.isChecked()) {
            Choices.add("entertainment");
        }
        if (cafeButton.isChecked()) {
            Choices.add("cafe");
        }
        if (cinemaButton.isChecked()) {
            Choices.add("cinema");
        }
        if (ahwaButton.isChecked()) {
            Choices.add("ahwa");
        }
        if (dessertButton.isChecked()) {
            Choices.add("others");
        }
        return Choices;
    }



/*    private void getWebservice(){

        RequestQueue queue = Volley.newRequestQueue(getContext());


        String url = "http://10.0.3.2/GP2/getAllitems.php";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                // Log.d("json", response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    for(int i= 0; i<response.getJSONArray("ItemsResponse").length() ;i++){
                        myItemList.add( new KhrogaItem(  (JSONObject)response.getJSONArray("ItemsResponse").get(i)) );
                        Log.e("testKim", "" + myItemList.size());

                        //(((KhrogaItem) response.getJSONArray("ItemsResponse").get(i)).getID());
                *//*String email = response.getString("email");
                JSONObject phone = response.getJSONObject("phone");
                String home = phone.getString("home");
                String mobile = phone.getString("mobile");

                jsonResponse = "";
                jsonResponse += "Name: " + name + "\n\n";
                jsonResponse += "Email: " + email + "\n\n";
                jsonResponse += "Home: " + home + "\n\n";
                jsonResponse += "Mobile: " + mobile + "\n\n";

                txtResponse.setText(jsonResponse);*//*
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(),
                            "KarimoError: " + e.getMessage(),
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



        queue.add(jsonObjReq);



    }*/

    public void showDialogue() {


        list_dialog = new Dialog(getContext());

        list_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        list_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        list_dialog.setContentView(R.layout.budget_pop_up);

        list_dialog.show();
    }
}

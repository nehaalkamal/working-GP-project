package com.example.samsung.gp;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.samsung.gp.Adapter.CommentAdapter;
import com.example.samsung.gp.Adapter.spinnerAdapter;
import com.example.samsung.gp.Model.CommentItem;
import com.example.samsung.gp.Model.KhrogaItem;
import com.example.samsung.gp.app.AppConfig;
import com.example.samsung.gp.app.AppController;
import com.example.samsung.gp.helper.SQLiteHandler;
import com.example.samsung.gp.helper.SessionManager;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class item_detailsFragment extends Fragment {

    Spinner spinner;
    String[] spinnerList = {
            "-",
            "1",
            "2",
            "3",
            "4",
            "5"

    };

    private static final String TAG = item_details.class.getSimpleName();
    static SQLiteHandler db;
    private SessionManager session;
    static HashMap<String, String> user;
    private EditText txtComment;
    private ImageButton CommentImage;
    private ListView CommentList;
    public CommentAdapter myAdapter;
    private static ArrayList<CommentItem> myCommentList = new ArrayList<>();
    public item_detailsFragment() {
    }

    /*  if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pop_up_listing,parent,false);
        }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView2 = inflater.inflate(R.layout.fragment_item_details, container, false);

        Intent intent = getActivity().getIntent();

        if (intent != null) {

            KhrogaItem DetailedItem = (KhrogaItem) intent.getSerializableExtra("DetailedItem");
            ((TextView) rootView2.findViewById(R.id.txtmainname)).setText(DetailedItem.getName());
            ((TextView) rootView2.findViewById(R.id.txtphoneno)).setText(DetailedItem.getPhone());
            ((TextView) rootView2.findViewById(R.id.txtLocation)).setText(DetailedItem.getAddress());
            ((TextView) rootView2.findViewById(R.id.txtcuisine)).setText(DetailedItem.getCuisine());
            ((TextView) rootView2.findViewById(R.id.txtrate)).setText(DetailedItem.getRate());
            ((TextView) rootView2.findViewById(R.id.txtfeaturesin)).setText(DetailedItem.getFeatures());
            ((TextView) rootView2.findViewById(R.id.txtopenhoursin)).setText(DetailedItem.getOpenHours());


            Picasso.with(getActivity()).load(DetailedItem.getImage()).placeholder(R.drawable.no_image).into(((ImageView) rootView2.findViewById(R.id.placelogo)));


            String ItemId = DetailedItem.getID();
            myCommentList.clear();
            GetComments(ItemId);
            spinner= (Spinner) rootView2.findViewById(R.id.spinner1);
            txtComment = (EditText) rootView2.findViewById(R.id.txtcomment);
            CommentImage = (ImageButton) rootView2.findViewById(R.id.ImageComment);
            CommentList = (ListView) rootView2.findViewById(R.id.commentslist);
            myAdapter = new CommentAdapter(getActivity(), myCommentList);
            CommentList.setAdapter(myAdapter);
            final String UserName = getUsername(getActivity());
            final String UserId = getUserId(getActivity());
            final String UserImage = getUserImage(getActivity());
            final String ItemID = DetailedItem.getID();
            session = new SessionManager(getActivity());
            final item_details item = new item_details();
            spinnerAdapter adapter = new spinnerAdapter(getActivity(), android.R.layout.simple_list_item_1);
            adapter.addAll(spinnerList);
            adapter.add("-");
            spinner.setAdapter(adapter);
            spinner.setSelection(adapter.getCount());
            CommentImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (session.isLoggedIn()) {
                        if(!txtComment.getText().toString().isEmpty()) {
                            String spinnerValue = spinner.getSelectedItem().toString();
                            if (spinnerValue.equals(null)) {
                                spinnerValue.equals("-");
                            }

                            InsertComment(UserId, ItemID, txtComment.getText().toString(), spinnerValue);
                            autoRefresh();
                        }
                        else{
                            Toast.makeText(getActivity(),
                                    "Write your review!", Toast.LENGTH_LONG)
                                    .show();
                        }
                    } else {
                        Toast.makeText(getActivity(),
                                "Please Log In to be able to write reviews", Toast.LENGTH_LONG)
                                .show();
                    }
                }
            });

        }
        return rootView2;
        }

    public String getUsername(Context context) {
        db = new SQLiteHandler(context);
        user = db.getUserDetails();
        String name = user.get("name");
        return name;
    }

    public String getUserId(Context context) {
        db = new SQLiteHandler(context);
        user = db.getUserDetails();
        String id = user.get("uid");
        return id;
    }

    public String getUserImage(Context context) {
        db = new SQLiteHandler(context);
        user = db.getUserDetails();
        String Image = user.get("UserImage");
        return Image;
    }

    //Inserting Comments
    private void InsertComment(final String UserId,
                               final String ItemId, final String CommentTxt, final String rate) {
        // Tag used to cancel the request
        String tag_string_req = "Review@itemDetails";


        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_Insert_comment, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "reviews Response: " + response.toString());


                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                       /* String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");
                        String email = user.getString("email");
                        String created_at = user
                                .getString("created_at");*/

                     /*   for(int i= 0; i<jObj.getJSONArray("ItemsResponse").length() ;i++){
                        MyCommentList.add(new CommentItem((JSONObject) jObj.getJSONArray("ItemsResponse").get(i)));

                    }*/
                        // Log.e("testSuccessWebservice", "" + MyCommentList.size());

                        Toast.makeText(getActivity(),
                                "review added", Toast.LENGTH_LONG).show();


                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getActivity(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("UserId", UserId);
                params.put("ItemId", ItemId);
                params.put("CommentTxt", CommentTxt);
                params.put("rate", rate);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, TAG);
    }


    private void GetComments(final String ItemId) {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());


        JsonObjectRequest strReq = new JsonObjectRequest(Request.Method.GET,
                AppConfig.URL_Get_comments+"?ItemId="+ItemId, null,new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {

                    for(int i= 0; i<response.getJSONArray("ItemsResponse").length() ;i++){
                        myCommentList.add(new CommentItem((JSONObject) response.getJSONArray("ItemsResponse").get(i)));


                    }
                    Log.e("testSuccessWebservice", "" + myCommentList.size());

                    myAdapter.notifyDataSetChanged();
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
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                progressDialog.dismiss();

            }
        });

        // Adding request to request queue

        AppController.getInstance().addToRequestQueue(strReq,TAG);


    }



    public void autoRefresh() {
        myCommentList.clear();
        Intent intent = getActivity().getIntent();
        getActivity().overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getActivity().finish();
        getActivity().overridePendingTransition(0, 0);
        startActivity(intent);
        myAdapter.notifyDataSetChanged();

    }

    }



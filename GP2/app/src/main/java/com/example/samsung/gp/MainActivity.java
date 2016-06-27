package com.example.samsung.gp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.samsung.gp.app.AppConfig;
import com.example.samsung.gp.app.AppController;
import com.example.samsung.gp.helper.SQLiteHandler;
import com.example.samsung.gp.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Spinner List;
    private EditText FirstName;
    private EditText LastName;
    private EditText Email;
    private EditText Password;
    private EditText ConfirmPassword;
    private Button StartButton;

    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Sign Up");
        List = (Spinner) findViewById(R.id.spinner1);
        FirstName=(EditText) findViewById(R.id.txtname);
        Email=(EditText) findViewById(R.id.txtemail);
        Password=(EditText) findViewById(R.id.txtpassword);
        ConfirmPassword=(EditText) findViewById(R.id.txtpassword2);
        StartButton=(Button)findViewById(R.id.startbutton);
        //back=(Button)findViewById(R.id.btnLinkToLoginScreen);


        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Check if user is already logged in or not
        if (
                session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(MainActivity.this,
                    HomeActivity.class);//13/6: karim kant core_screen b2t HomeActivity
            startActivity(intent);
            finish();
        }

        // Register Button Click event
        StartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name = FirstName.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String Confirmconpassword = ConfirmPassword.getText().toString().trim();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    if(Confirmconpassword.equals(password)){
                    registerUser(name, email, password,".",".",".",".","http://i.imgur.com/FSSMALk.png");/*
                    Intent intent = new Intent(MainActivity.this,
                            HomeActivity.class);//13/6: karim kant core_screen b2t HomeActivity
                    startActivity(intent);
                    finish();*/}
                    else{

                        Toast.makeText(getApplicationContext(),
                                "Confirm Password are worng!", Toast.LENGTH_LONG)
                                .show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });



        // Link to Login Screen
      /*  back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
*/
    }

    @Override //for Font
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     * */
    private void registerUser(final String name, final String email,
                              final String password, final String Location, final String birthDate
            , final String Gender, final String Biography, final String UserImage) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");  //// Biography birthDate Gender Location UserImage
                        String name = user.getString("name");
                        String email = user.getString("email");
                        String location = user.getString("Location");
                        String biography = user.getString("Biography");
                        String userimage = user.getString("UserImage");
                        String gender = user.getString("Gender");
                        String birthdate = user.getString("birthDate");
                        String created_at = user
                                .getString("created_at");

                        // Inserting row in users table
                        db.addUser(name, email,Password.getText().toString(),uid,location,biography,gender,userimage,birthdate, created_at);

                        Toast.makeText(getApplicationContext(), "User successfully registered!", Toast.LENGTH_LONG).show();

                        session.setLogin(true);
                        // Launch login activity

                        Intent intent = new Intent(
                                MainActivity.this,
                                HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
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
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("Location", Location);
                params.put("birthDate", birthDate);
                params.put("Gender", Gender);
                params.put("Biography", Biography);
                params.put("UserImage", UserImage);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return(super.onOptionsItemSelected(item));
    }


    @Override
    public void onBackPressed() {

            Intent i = new Intent(getApplicationContext(),
                    LoginActivity.class);
            startActivity(i);
            finish();

    }
}

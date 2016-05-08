package com.example.samsung.gp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity {

    private Spinner List;
    private EditText FirstName;
    private EditText LastName;
    private EditText Email;
    private EditText Password;
    private EditText ConfirmPassword;
    private Button StartButton;

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
        LastName=(EditText) findViewById(R.id.txtlastname);
        Email=(EditText) findViewById(R.id.txtemail);
        Password=(EditText) findViewById(R.id.txtpassword);
        ConfirmPassword=(EditText) findViewById(R.id.txtpassword2);
        StartButton=(Button)findViewById(R.id.startbutton);

        ArrayAdapter<String> adapter;
        ArrayList<String> list = new ArrayList<String>();
        list.add("Maadi");
        list.add("Nasr City");
        list.add("Shoubra");
        list.add("Helwan");
        list.add("Heliopolis");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,
                R.id.txtSpinner_item, list);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        List.setAdapter(adapter);


        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override //for Font
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}

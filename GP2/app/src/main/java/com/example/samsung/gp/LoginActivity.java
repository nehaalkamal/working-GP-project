package com.example.samsung.gp;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText Password;
    private EditText Email;
    private Button Sign_in_button ;
    private Image Facebook;
    private Image Twitter;
    private Image Gmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email=(EditText) findViewById(R.id.email);
        Password=(EditText) findViewById(R.id.password);
        Sign_in_button=(Button) findViewById(R.id.email_sign_in_button);
    }

}

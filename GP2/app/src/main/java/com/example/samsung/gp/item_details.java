package com.example.samsung.gp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.samsung.gp.helper.SQLiteHandler;
import com.example.samsung.gp.helper.SessionManager;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class item_details extends AppCompatActivity {
    private SQLiteHandler db;
    private SessionManager session;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // toolbar.setTitle("Details");

       //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDisplayShowHomeEnabled(true);

       getSupportActionBar().setTitle("Details");

    //session manager
    session = new SessionManager(getApplicationContext());

    // SQLite database handler
    db = new SQLiteHandler(getApplicationContext());



    }


    @Override //for Font
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_core_screen, menu);

        if(!session.isLoggedIn()){
            menu.findItem(R.id.action_profile).setVisible(false);
            menu.findItem(R.id.action_favourites).setVisible(false);
            menu.findItem(R.id.action_logout).setVisible(false);
            menu.findItem(R.id.action_login).setVisible(true);
        }


        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_profile:{
                startActivity(new Intent(this, user_profile.class));
                return true;
            }
            case R.id.action_favourites: {
                startActivity(new Intent(this, Favourites.class));
                return true;
            }
            case R.id.action_logout: {

                logoutUser();
                return true;
            }
            case R.id.action_login: {

                startActivity(new Intent(this, LoginActivity.class));
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

}
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

public class KhrogatPackages extends AppCompatActivity {
    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khrogat_packages);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("Suggested Khrogat");

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
                startActivity(new Intent(this, user_profile.class));// profile
                return true;
            }
            case R.id.action_favourites: {

                startActivityForResult(new Intent(this, Favourites.class), 1);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                //da y3ny gy mn el favourite activity fa lazm
                // arefresh 3san lw el user 3ml le 7aga unfav f Favourites.Activity mtzhrloosh
                //enha favourited f ydos 3liha tany f t7sl moshkla
                overridePendingTransition(0, 0);
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
            }
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


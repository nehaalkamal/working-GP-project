package com.example.samsung.gp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.samsung.gp.Adapter.FavDatabase;
import com.example.samsung.gp.Adapter.PackagesAdapter;
import com.example.samsung.gp.Adapter.listingAdapter;
import com.example.samsung.gp.Model.KhrogaItem;
import com.example.samsung.gp.Model.KhrogaPackage;
import com.example.samsung.gp.helper.SQLiteHandler;
import com.example.samsung.gp.helper.SessionManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Favourites extends AppCompatActivity {
    PackagesAdapter packagesAdapter;
    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(R.string.title_activity_Favourites);

        //session manager
        session = new SessionManager(getApplicationContext());

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());




        if (FavDatabase.getFKhrogaPackagesList(this) != null) { //User has favourites
            setContentView(R.layout.fragment_khrogat_packages);
            packagesAdapter = new PackagesAdapter(this, FavDatabase.getFKhrogaPackagesList(this), getTitle().toString());
            GridView packagesGridView = (GridView) findViewById(R.id.GridView_Packages);
            packagesGridView.setAdapter(packagesAdapter);

            packagesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    KhrogaPackage selectedPackage = packagesAdapter.getItem(position);

                    showDialogue(selectedPackage.getKhrogaPackage(), Favourites.this);

                }
            });

            packagesGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    KhrogaPackage selectedPackage = packagesAdapter.getItem(position);

                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "\n\n");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, selectedPackage.shareThatPackage());
                    startActivity(Intent.createChooser(sharingIntent, "Share khoroga"));
                    return true;
                }
            });


        } else { //Has no favourites
            setContentView(R.layout.activity_favourites);
            TextView t = (TextView) findViewById(R.id.textViewtest);
            Picasso.with(this).load(R.drawable.bigheart).resize(600, 600).centerInside().into((ImageView) findViewById(R.id.imageViewHeart));
            RelativeLayout view = (RelativeLayout) findViewById(R.id.fav_view);
            view.setBackgroundColor(Color.parseColor("#E2D1AF"));
            t.setText("NO FAVOURITES YET");

        }

    }

    @Override //for Font
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fav_menu, menu);

        if (FavDatabase.getFKhrogaPackagesList(this) == null) {

            MenuItem item = menu.findItem(R.id.clear_fav);
            item.setVisible(false);
        }


        if(!session.isLoggedIn()){
            menu.findItem(R.id.action_profile).setVisible(false);
            menu.findItem(R.id.clear_fav).setVisible(false);
            menu.findItem(R.id.action_logout).setVisible(false);
            menu.findItem(R.id.action_login).setVisible(true);
        }


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.



        switch (item.getItemId()) {
            case R.id.action_profile:{
                startActivity(new Intent(this, user_profile.class));
                return true;
            }
            case R.id.clear_fav:
            {
                alertClearWarning();
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


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    public void alertClearWarning() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Clear all favourites?");


        alertDialogBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialogBuilder.setPositiveButton("clear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                FavDatabase.clearMyFav(Favourites.this);
                FavDatabase.updateBEfromSP(Favourites.this);
                autoRefresh();
            }
        });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    public void autoRefresh() {

        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        setResult(RESULT_OK, intent);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);

    }

    public void showDialogue(final ArrayList<KhrogaItem> selectedPackage, final Context context) {


        Dialog list_dialog = new Dialog(context);

        list_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        list_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        list_dialog.setContentView(R.layout.list_dialog);

        ListView list = (ListView) list_dialog.findViewById(R.id.component_list);
        ArrayAdapter<KhrogaItem> adapter = new listingAdapter(context, selectedPackage);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KhrogaItem DetailedItem = selectedPackage.get(position);
                Intent DetailIntent = new Intent(context, item_details.class);
                DetailIntent.putExtra("DetailedItem", DetailedItem);
                startActivity(DetailIntent);
            }
        });
        Button positiveButton = (Button) list_dialog.findViewById(R.id.positive_button);
        positiveButton.setVisibility(View.GONE);
        list_dialog.show();
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

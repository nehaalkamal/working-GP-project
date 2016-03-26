package com.example.samsung.gp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class CoreScreen extends AppCompatActivity {

    private Spinner AreaList;
    private ImageButton foodButton;
    private ImageButton cafeButton;
    private ImageButton cinemaButton;
    private ImageButton entertainmentButton;
    private ImageButton ahwaButton;
    private ImageButton concertsButton;
    private Button  yallaButton ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Nokhrog Fen");

        setContentView(R.layout.activity_core_screen);


        AreaList = (Spinner) findViewById(R.id.AreaSpinner);
        ArrayAdapter<String> adapter;
        ArrayList<String> amakenList = new ArrayList<String>();
        amakenList.add("Maadi");
        amakenList.add("Nasr City");
        amakenList.add("Shoubra");
        amakenList.add("Helwan");
        amakenList.add("Heliopolis");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,
                R.id.txtSpinner_item,amakenList);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        AreaList.setAdapter(adapter);

        foodButton=(ImageButton)findViewById(R.id.food);
        cafeButton=(ImageButton)findViewById(R.id.cafe);
        entertainmentButton=(ImageButton)findViewById(R.id.game);
        cinemaButton=(ImageButton)findViewById(R.id.cinema);
        ahwaButton=(ImageButton)findViewById(R.id.ahwa);
        concertsButton=(ImageButton)findViewById(R.id.concert);
        yallaButton=(Button)findViewById(R.id.YallaButton);


        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(true);

            }
        });

        entertainmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(true);
            }
        });

        cafeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(true);

            }
        });
       cinemaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(true);

            }
        });
       ahwaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(true);
            }
        });
        concertsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(true);
            }
        });

        yallaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

}

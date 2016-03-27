package com.example.samsung.gp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class core_screenFragment extends Fragment {


    private Spinner AreaList;
    private ImageButton foodButton;
    private ImageButton cafeButton;
    private ImageButton cinemaButton;
    private ImageButton entertainmentButton;
    private ImageButton ahwaButton;
    private ImageButton concertsButton;
    private Button  yallaButton ;
    public core_screenFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_core_screen, container, false);


        AreaList = (Spinner)rootView.findViewById(R.id.AreaSpinner);
        ArrayAdapter<String> adapter;
        ArrayList<String> amakenList = new ArrayList<String>();
        amakenList.add("Maadi");
        amakenList.add("Nasr City");
        amakenList.add("Shoubra");
        amakenList.add("Helwan");
        amakenList.add("Heliopolis");
        adapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item,R.id.txtSpinner_item,amakenList);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        AreaList.setAdapter(adapter);

        foodButton=(ImageButton)rootView.findViewById(R.id.food);
        cafeButton=(ImageButton)rootView.findViewById(R.id.cafe);
        entertainmentButton=(ImageButton)rootView.findViewById(R.id.game);
        cinemaButton=(ImageButton)rootView.findViewById(R.id.cinema);
        ahwaButton=(ImageButton)rootView.findViewById(R.id.ahwa);
        concertsButton=(ImageButton)rootView.findViewById(R.id.concert);
        yallaButton=(Button)rootView.findViewById(R.id.YallaButton);


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

        return rootView;
    }


}

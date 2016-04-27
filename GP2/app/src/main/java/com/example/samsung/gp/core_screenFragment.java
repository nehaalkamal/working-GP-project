package com.example.samsung.gp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class core_screenFragment extends Fragment {


    private Spinner AreaList;
    private EditText budget;
    private CheckBox foodButton;
    private CheckBox cafeButton;
    private CheckBox cinemaButton;
    private CheckBox entertainmentButton;
    private CheckBox ahwaButton;
    private CheckBox dessertButton;
    private Button  yallaButton ;
    public core_screenFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_core_screen, container, false);




        AreaList = (Spinner)rootView.findViewById(R.id.AreaSpinner);
        ArrayAdapter<String> adapter;
        final ArrayList<String> amakenList = new ArrayList<String>();
        amakenList.add("Maadi");
        amakenList.add("Nasr City");
        amakenList.add("Shoubra");
        amakenList.add("Helwan");
        amakenList.add("Heliopolis");
        adapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item,R.id.txtSpinner_item,amakenList);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        AreaList.setAdapter(adapter);

        final ArrayList<String> choices = new ArrayList<String>();

        budget = (EditText) rootView.findViewById(R.id.budgetvalue);
        foodButton=(CheckBox)rootView.findViewById(R.id.food);
        cafeButton=(CheckBox)rootView.findViewById(R.id.cafe);
        entertainmentButton=(CheckBox)rootView.findViewById(R.id.game);
        cinemaButton=(CheckBox)rootView.findViewById(R.id.cinema);
        ahwaButton=(CheckBox)rootView.findViewById(R.id.ahwa);
        dessertButton =(CheckBox)rootView.findViewById(R.id.dessert);
        yallaButton=(Button)rootView.findViewById(R.id.YallaButton);


        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(choices.contains("restaurant")){
                    choices.remove("restaurant");
                    v.setSelected(false);
                }
                else {

                    choices.add("restaurant");
                    v.setSelected(true);
                }
            }
        });

        entertainmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(choices.contains("entertainment")){
                    choices.remove("entertainment");
                    v.setSelected(false);
                }
                else {

                    choices.add("entertainment");
                    v.setSelected(true);
                }

            }
        });

        cafeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(choices.contains("cafe")){
                    choices.remove("cafe");
                    v.setSelected(false);
                }
                else {

                    choices.add("cafe");
                    v.setSelected(true);
                }
            }
        });
        cinemaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choices.contains("cinema")){
                    choices.remove("cinema");
                    v.setSelected(false);
                }
                else {

                    choices.add("cinema");
                    v.setSelected(true);
                }


            }
        });
        ahwaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choices.contains("ahwa")){
                    choices.remove("ahwa");
                    v.setSelected(false);
                }
                else {

                    choices.add("ahwa");
                    v.setSelected(true);
                }

            }
        });
        dessertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choices.contains("others")) {
                    choices.remove("others");
                    v.setSelected(false);
                } else {

                    choices.add("others");
                    v.setSelected(true);
                }

            }
        });

        yallaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String budgetValue = budget.getText().toString();
                String spinnerValue = AreaList.getSelectedItem().toString();
                Intent intent = new Intent(getActivity(),KhrogatPackages.class);

                intent.putExtra("budget", budgetValue);
                intent.putExtra("choices",choices);
                intent.putExtra("location", spinnerValue);
                getActivity().startActivity(intent);

            }
        });

        return rootView;
    }



}

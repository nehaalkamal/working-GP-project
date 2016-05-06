package com.example.samsung.gp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class core_screenFragment extends Fragment {
    Spinner spinner;
    String[] spinnerList = {
            "Maadi",
            "Nasr City"

    };

    private EditText budget;
    private CheckBox foodButton;
    private CheckBox cafeButton;
    private CheckBox cinemaButton;
    private CheckBox entertainmenttButton;
    private CheckBox ahwaButton;
    private CheckBox dessertButton;
    private Button yallaButton;
    String spinnerValue;

    public core_screenFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_core_screen, container, false);


        final Context context = getContext();

        final Animation animAlpha = AnimationUtils.loadAnimation(context, R.anim.anim_alpha);



        budget = (EditText) rootView.findViewById(R.id.budgetvalue);
        foodButton = (CheckBox) rootView.findViewById(R.id.food);
        cafeButton = (CheckBox) rootView.findViewById(R.id.cafe);
        entertainmenttButton = (CheckBox) rootView.findViewById(R.id.game);
        cinemaButton = (CheckBox) rootView.findViewById(R.id.cinema);
        ahwaButton = (CheckBox) rootView.findViewById(R.id.ahwa);
        dessertButton = (CheckBox) rootView.findViewById(R.id.dessert);
        yallaButton = (Button) rootView.findViewById(R.id.YallaButton);
        spinner= (Spinner) rootView.findViewById(R.id.spinner1);


/*
        Spinner spinner = (Spinner) rootView.findViewById(R.id.AreaSpinner);
        ArrayAdapter<CharSequence> Spinneradapter = ArrayAdapter.createFromResource(context, R.array.AreasArray, android.R.layout.simple_spinner_dropdown_item);

        Spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long id) {

                spinnerValue = arg0.getItemAtPosition(pos)
                        .toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        spinner.setAdapter(Spinneradapter);
        */



        spinnerAdapter adapter = new spinnerAdapter(getContext(), android.R.layout.simple_list_item_1);
        adapter.addAll(spinnerList);
        adapter.add("Choose Area");
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
// TODO Auto-generated method stub

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
// TODO Auto-generated method stub
            }
        });


        cafeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);

            }
        });
        cinemaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);



            }
        });
        ahwaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);


            }
        });
        dessertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);

            }
        });


        yallaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String budgetValue = budget.getText().toString();
                //  String spinnerValue = spinner.getSelectedItem().toString();
                Intent intent = new Intent(getActivity(), KhrogatPackages.class);

                intent.putExtra("budget", budgetValue);
                intent.putExtra("choices", getCheckedButtons());
                intent.putExtra("location", spinnerValue);

                getActivity().startActivity(intent);

            }

        });
        return rootView;
    }


    ArrayList<String> getCheckedButtons() {
        ArrayList<String> Choices = new ArrayList<>();
        if (foodButton.isChecked()) {
            Choices.add("restaurant");
        }
        if (entertainmenttButton.isChecked()) {
            Choices.add("entertainment");
        }
        if (cafeButton.isChecked()) {
            Choices.add("cafe");
        }
        if (cinemaButton.isChecked()) {
            Choices.add("cinema");
        }
        if (ahwaButton.isChecked()) {
            Choices.add("ahwa");
        }
        if (dessertButton.isChecked()) {
            Choices.add("others");
        }
        return Choices;
    }



}

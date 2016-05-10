package com.example.samsung.gp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.example.samsung.gp.Adapter.PackagesAdapter;
import com.example.samsung.gp.Model.KhrogaItem;
import com.example.samsung.gp.Model.KhrogaPackage;
import com.example.samsung.gp.Adapter.listingAdapter;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class KhrogatPackagesFragment extends Fragment {

    private PackagesAdapter packagesAdapter;
    private GridView packagesGridView;
    private Dialog list_dialog;

    public KhrogatPackagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_khrogat_packages, container, false);


        Intent intent = getActivity().getIntent();

//if (intent != null) {

        ArrayList<String> choices = intent.getStringArrayListExtra("choices");
        String budget = intent.getStringExtra("budget");
        String location = intent.getStringExtra("location");


        if (getActivity() != null) {
//Da hytshal 3shan n5li el data dynamically
            KhrogaItem KI = new KhrogaItem("4d7cd8f486cfa143c467d1a0", "Peking", "70", "Restaurant", "43 Rd. 250", "8.2", "12:00 PM to 12:00 AM", "Chinese Restaurant", "WiFi, No Smoking Area, Alcohol", "2016078", "http://i.imgur.com/pcQAn5l.png", "Maadi");
            KhrogaItem KI2 = new KhrogaItem("4d846cba5e70224b61760c09", "Auntie Anne's", "40", "Restaurant", "76 Rd 9 (82 Rd)76 Rd 9 (82 Rd)76 Rd 9 (82 Rd)76 Rd 9 (82 Rd)76 Rd 9 (82 Rd)", "7.9", "9:30 AM to 1:00 AM", "German RestaurantGerman RestaurantGerman RestaurantGerman RestaurantGerman Restaurant", "WiFi, Outdoor", "2016629", "http://i.imgur.com/pcQAn5l.png", "Maadi");
            KhrogaItem KI3 = new KhrogaItem("4d846cba5e70224b61760c09", "Mcdonald's", "35", "cafes", "76 Rd 9 (82 Rd)", "10", "9:30 AM to 1:00 AM", "German Restaurant", "WiFi, Outdoor", "2016629", "http://i.imgur.com/pcQAn5l.png", "Maadi");
            KhrogaItem KI4 = new KhrogaItem("4d846cba5e70224b61760c09", "Nola- نولا", "20", "others", "76 Rd 9 (82 Rd)", "3.5", "9:30 AM to 1:00 AM", "German Restaurant", "WiFi, Outdoor", "2016629", "http://i.imgur.com/pcQAn5l.png", "Maadi");

            ArrayList<KhrogaItem> KL = new ArrayList<>();
            KL.add(KI);
            KL.add(KI2);

            ArrayList<KhrogaItem> KL2 = new ArrayList<>();
            KL2.add(KI3);
            KL2.add(KI4);

            ArrayList<KhrogaItem> KL3 = new ArrayList<>();
            KL3.add(KI);
            KL3.add(KI3);
            KL3.add(KI2);

            KL3.add(KI4);

            KhrogaPackage KP = new KhrogaPackage();

            KhrogaPackage KP2 = new KhrogaPackage();
            KhrogaPackage KP3 = new KhrogaPackage();

            KhrogaPackage KP4 = new KhrogaPackage();
            KhrogaPackage KP5 = new KhrogaPackage();

            KP.setKhrogaPackage(KL);
            KP.setFavorited(true);

            KP2.setKhrogaPackage(KL2);

            KP3.setKhrogaPackage(KL3);
            KP4.setKhrogaPackage(KL2);
            KP5.setKhrogaPackage(KL3);
            KP5.setFavorited(true);


            ArrayList<KhrogaPackage> last = new ArrayList<>();
            last.add(KP);
            last.add(KP2);
            last.add(KP3);
            last.add(KP4);
            last.add(KP5);

            packagesAdapter = new PackagesAdapter(getActivity(), last);
            packagesGridView = (GridView) rootView.findViewById(R.id.GridView_Packages);
            packagesGridView.setAdapter(packagesAdapter);

        }

        //    }


        packagesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                KhrogaPackage selectedPackage = packagesAdapter.getItem(position);
                showDialogue(selectedPackage.getKhrogaPackage());

            }
        });


        return rootView;
    }

    private void showDialogue(final ArrayList<KhrogaItem> selectedPackage) {


        list_dialog = new Dialog(getContext());
        list_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        list_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        list_dialog.setContentView(R.layout.list_dialog);

        ListView list = (ListView) list_dialog.findViewById(R.id.component_list);
        ArrayAdapter<KhrogaItem> adapter = new listingAdapter(getContext(), selectedPackage);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KhrogaItem DetailedItem = selectedPackage.get(position);
                Intent DetailIntent = new Intent(getActivity(), item_details.class);
                DetailIntent.putExtra("DetailedItem", DetailedItem);
                startActivity(DetailIntent);
            }
        });
        Button positiveButton = (Button) list_dialog.findViewById(R.id.positive_button);

        positiveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                list_dialog.dismiss();
            }
        });

        list_dialog.show();
    }
}
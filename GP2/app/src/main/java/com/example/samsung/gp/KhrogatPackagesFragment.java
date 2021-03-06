package com.example.samsung.gp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.example.samsung.gp.Adapter.listingAdapter;
import com.example.samsung.gp.Model.KhrogaItem;
import com.example.samsung.gp.Model.KhrogaPackage;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A placeholder fragment containing a simple view.
 */
public class KhrogatPackagesFragment extends Fragment {

    private PackagesAdapter packagesAdapter;
    private GridView packagesGridView;
    private Dialog list_dialog;
    private static ArrayList<KhrogaItem> myListItem=new ArrayList<>();
    public static int SummtionBudget = 0;
    public boolean HasItem = false;
    public boolean HasFood = false;
    public boolean HasCafe = false;
    public boolean HasCafe_food = false;
    public boolean HasFun = false;
    public boolean HasCinema = false;
    public boolean HasOthers = false;
    public boolean HasAhwa = false;

    public static  ArrayList<KhrogaPackage> favouritesList = new ArrayList<>(); //not used?

    public KhrogatPackagesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_khrogat_packages, container, false);


        Intent intent = getActivity().getIntent();

if (intent != null) {


      myListItem= (ArrayList<KhrogaItem>) intent.getSerializableExtra("myListItem");
        Log.e("testIntent",""+myListItem.size());

    int UserBudget =Integer.parseInt(intent.getStringExtra("budget"));

    ArrayList<KhrogaPackage> last = new ArrayList<>();
    ArrayList<KhrogaItem> myItem= new ArrayList<>();
    ArrayList<KhrogaItem> foodItem= new ArrayList<>();
    ArrayList<KhrogaItem> cafeItem= new ArrayList<>();
    ArrayList<KhrogaItem> cafe_foodItem= new ArrayList<>();
    ArrayList<KhrogaItem> cinemaItem= new ArrayList<>();
    ArrayList<KhrogaItem> othersItem= new ArrayList<>();
    ArrayList<KhrogaItem> ahwaItem= new ArrayList<>();
    ArrayList<KhrogaItem> funItem= new ArrayList<>();

    for (int j = 0 ; j < myListItem.size() ; j++){
        if (myListItem.get(j).getCategory().equalsIgnoreCase("Restaurant")) {
            foodItem.add(myListItem.get(j));
        }
        if (myListItem.get(j).getCategory().equalsIgnoreCase("Cafe")) {
            cafeItem.add(myListItem.get(j));
        }
        if (myListItem.get(j).getCategory().equalsIgnoreCase("Cafe_Restaurant")) {
            cafe_foodItem.add(myListItem.get(j));
        }
        if (myListItem.get(j).getCategory().equalsIgnoreCase("Cinema")) {
            cinemaItem.add(myListItem.get(j));
        }
        if (myListItem.get(j).getCategory().equalsIgnoreCase("Others")) {
            othersItem.add(myListItem.get(j));
        }
        if (myListItem.get(j).getCategory().equalsIgnoreCase("Ahwa")) {
            ahwaItem.add(myListItem.get(j));
        }
        if (myListItem.get(j).getCategory().equalsIgnoreCase("Entertainment")) {
            funItem.add(myListItem.get(j));
        }

    }
    myItem.addAll(myListItem);

        if (getActivity() != null) {


            for(int l = 0 ; l < myItem.size() ; l++){
                ArrayList<KhrogaItem> KL = new ArrayList<>();
                KhrogaPackage KP = new KhrogaPackage();
                HasItem = false;
                HasFood = false;
                HasCafe = false;
                HasCafe_food = false;
                HasFun = false;
                HasCinema = false;
                HasOthers = false;
                HasAhwa = false;
                for(int i = 0 ; i < myListItem.size() ; i++) {
                    if (myListItem.get(i).getCategory().equalsIgnoreCase("Restaurant")) {
                        if (KL.size()==0) {
                            if (Integer.parseInt(myListItem.get(i).getPrice()) <= (UserBudget - SummtionBudget)) { // 3lashan 23ml el budget blzbt ha5liha summtionbudget < userbudget - summtionbudget
                                for (int k = 0 ; k <foodItem.size() ; k++) {
                                    if(foodItem.get(k).getID().equals(myListItem.get(i).getID())) {
                                        KL.add(foodItem.get(k));
                                        SummtionBudget += Integer.parseInt(foodItem.get(k).getPrice());
                                        foodItem.remove(foodItem.get(k));
                                    }
                                }
                            }

                        } else {

                            for (int h = 0; h < KL.size(); h++) {
                                if (KL.get(h).getCategory().equalsIgnoreCase("Restaurant")) {
                                    HasFood = true;
                                }
                            }
                            if (!HasFood) {
                                if (Integer.parseInt(myListItem.get(i).getPrice()) <= (UserBudget - SummtionBudget)) {
                                    for (int k = 0 ; k < foodItem.size() ; k++) {
                                        if(foodItem.get(k).getID().equals(myListItem.get(i).getID())) {
                                            KL.add(foodItem.get(k));
                                            SummtionBudget += Integer.parseInt(foodItem.get(k).getPrice());
                                            foodItem.remove(foodItem.get(k));
                                        }
                                    }


                                }
                            }
                        }
                    }
                    if (myListItem.get(i).getCategory().equalsIgnoreCase("Cafe")) {
                        if (KL.size()==0) {
                            if (Integer.parseInt(myListItem.get(i).getPrice()) <= (UserBudget - SummtionBudget)) {
                                for (int k = 0 ; k < cafeItem.size() ; k++) {
                                    if(cafeItem.get(k).getID().equals(myListItem.get(i).getID())) {
                                        KL.add(cafeItem.get(k));
                                        SummtionBudget += Integer.parseInt(cafeItem.get(k).getPrice());
                                        cafeItem.remove(cafeItem.get(k));
                                    }
                                }


                            }

                        } else {

                            for (int h = 0; h < KL.size(); h++) {
                                if (KL.get(h).getCategory().equalsIgnoreCase("Cafe")) {
                                    HasCafe = true;
                                }
                            }
                            if (!HasCafe) {
                                if (Integer.parseInt(myListItem.get(i).getPrice()) <= (UserBudget - SummtionBudget)) {
                                    for (int k = 0 ; k < cafeItem.size() ; k++) {
                                        if(cafeItem.get(k).getID().equals(myListItem.get(i).getID())) {
                                            KL.add(cafeItem.get(k));
                                            SummtionBudget += Integer.parseInt(cafeItem.get(k).getPrice());
                                            cafeItem.remove(cafeItem.get(k));
                                        }
                                    }


                                }
                            }
                        }
                    }
                    if (myListItem.get(i).getCategory().equalsIgnoreCase("Cafe_Restaurant")) {
                        if (KL.size()==0) {
                            if (Integer.parseInt(myListItem.get(i).getPrice()) <= (UserBudget - SummtionBudget)) {
                                for (int k = 0 ; k < cafe_foodItem.size() ; k++) {
                                    if(cafe_foodItem.get(k).getID().equals(myListItem.get(i).getID())) {
                                        KL.add(cafe_foodItem.get(k));
                                        SummtionBudget += Integer.parseInt(cafe_foodItem.get(k).getPrice());
                                        cafe_foodItem.remove(cafe_foodItem.get(k));
                                    }
                                }


                            }

                        } else {

                            for (int h = 0; h < KL.size(); h++) {
                                if (KL.get(h).getCategory().equalsIgnoreCase("Cafe_Restaurant")) {
                                    HasCafe_food = true;
                                }
                            }
                            if (!HasCafe_food) {
                                if (Integer.parseInt(myListItem.get(i).getPrice()) <= (UserBudget - SummtionBudget)) {
                                    for (int k = 0 ; k < cafe_foodItem.size() ; k++) {
                                        if(cafe_foodItem.get(k).getID().equals(myListItem.get(i).getID())) {
                                            KL.add(cafe_foodItem.get(k));
                                            SummtionBudget += Integer.parseInt(cafe_foodItem.get(k).getPrice());
                                            cafe_foodItem.remove(cafe_foodItem.get(k));
                                        }
                                    }


                                }
                            }
                        }
                    }
                    if (myListItem.get(i).getCategory().equalsIgnoreCase("Cinema")) {
                        if (KL.size()==0) {
                            if (Integer.parseInt(myListItem.get(i).getPrice()) <= (UserBudget - SummtionBudget)) {
                                for (int k = 0 ; k < cinemaItem.size() ; k++) {
                                    if(cinemaItem.get(k).getID().equals(myListItem.get(i).getID())) {
                                        KL.add(cinemaItem.get(k));
                                        SummtionBudget += Integer.parseInt(cinemaItem.get(k).getPrice());
                                        cinemaItem.remove(cinemaItem.get(k));
                                    }
                                }
                            }

                        } else {

                            for (int h = 0; h < KL.size(); h++) {
                                if (KL.get(h).getCategory().equalsIgnoreCase("Cinema")) {
                                    HasCinema = true;
                                }
                            }
                            if (!HasCinema) {
                                if (Integer.parseInt(myListItem.get(i).getPrice()) <= (UserBudget - SummtionBudget)) {
                                    for (int k = 0 ; k < cinemaItem.size() ; k++) {
                                        if(cinemaItem.get(k).getID().equals(myListItem.get(i).getID())) {
                                            KL.add(cinemaItem.get(k));
                                            SummtionBudget += Integer.parseInt(cinemaItem.get(k).getPrice());
                                            cinemaItem.remove(cinemaItem.get(k));
                                        }
                                    }

                                }
                            }
                        }
                    }
                    if (myListItem.get(i).getCategory().equalsIgnoreCase("Others")) {
                        if (KL.size()==0) {
                            if (Integer.parseInt(myListItem.get(i).getPrice()) <= (UserBudget - SummtionBudget)) {
                                for (int k = 0 ; k < othersItem.size() ; k++) {
                                    if(othersItem.get(k).getID().equals(myListItem.get(i).getID())) {
                                        KL.add(othersItem.get(k));
                                        SummtionBudget += Integer.parseInt(othersItem.get(k).getPrice());
                                        othersItem.remove(othersItem.get(k));
                                    }
                                }


                            }

                        } else {

                            for (int h = 0; h < KL.size(); h++) {
                                if (KL.get(h).getCategory().equalsIgnoreCase("Others")) {
                                    HasOthers = true;
                                }
                            }
                            if (!HasOthers) {
                                if (Integer.parseInt(myListItem.get(i).getPrice()) <= (UserBudget - SummtionBudget)) {
                                    for (int k = 0 ; k < othersItem.size() ; k++) {
                                        if(othersItem.get(k).getID().equals(myListItem.get(i).getID())) {
                                            KL.add(othersItem.get(k));
                                            SummtionBudget += Integer.parseInt(othersItem.get(k).getPrice());
                                            othersItem.remove(othersItem.get(k));
                                        }
                                    }


                                }
                            }
                        }
                    }
                    if (myListItem.get(i).getCategory().equalsIgnoreCase("Ahwa")) {
                        if (KL.size()==0) {
                            if (Integer.parseInt(myListItem.get(i).getPrice()) <= (UserBudget - SummtionBudget)) {
                                for (int k = 0 ; k < ahwaItem.size() ; k++) {
                                    if(ahwaItem.get(k).getID().equals(myListItem.get(i).getID())) {
                                        KL.add(ahwaItem.get(k));
                                        SummtionBudget += Integer.parseInt(ahwaItem.get(k).getPrice());
                                        ahwaItem.remove(ahwaItem.get(k));
                                    }
                                }


                            }

                        } else {

                            for (int h = 0; h < KL.size(); h++) {
                                if (KL.get(h).getCategory().equalsIgnoreCase("Ahwa")) {
                                    HasAhwa = true;
                                }
                            }
                            if (!HasAhwa) {
                                if (Integer.parseInt(myListItem.get(i).getPrice()) <= (UserBudget - SummtionBudget)) {
                                    for (int k = 0 ; k < ahwaItem.size() ; k++) {
                                        if(ahwaItem.get(k).getID().equals(myListItem.get(i).getID())) {
                                            KL.add(ahwaItem.get(k));
                                            SummtionBudget += Integer.parseInt(ahwaItem.get(k).getPrice());
                                            ahwaItem.remove(ahwaItem.get(k));
                                        }
                                    }


                                }
                            }
                        }
                    }
                    if (myListItem.get(i).getCategory().equalsIgnoreCase("Entertainment")) {
                        if (KL.size()==0) {
                            if (Integer.parseInt(myListItem.get(i).getPrice()) <= (UserBudget - SummtionBudget)) {
                                for (int k = 0 ; k < funItem.size() ; k++) {
                                    if(funItem.get(k).getID().equals(myListItem.get(i).getID())) {
                                        KL.add(funItem.get(k));
                                        SummtionBudget += Integer.parseInt(funItem.get(k).getPrice());
                                        funItem.remove(funItem.get(k));
                                    }
                                }


                            }

                        } else {

                            for (int h = 0; h < KL.size(); h++) {
                                if (KL.get(h).getCategory().equalsIgnoreCase("Entertainment")) {
                                    HasFun = true;
                                }
                            }
                            if (!HasFun) {
                                if (Integer.parseInt(myListItem.get(i).getPrice()) <= (UserBudget - SummtionBudget)) {
                                    for (int k = 0 ; k < funItem.size() ; k++) {
                                        if(funItem.get(k).getID().equals(myListItem.get(i).getID())) {
                                            KL.add(funItem.get(k));
                                            SummtionBudget += Integer.parseInt(funItem.get(k).getPrice());
                                            funItem.remove(funItem.get(k));

                                        }
                                    }


                                }
                            }
                        }

                    }
                    /*for(int h=0 ; h < KL.size() ; h++){
                        if(KL.get(h).getID().equals(myListItem.get(i).getID())){
                            HasItem = true;
                        }
                    }
                    if(HasItem) {
                        myListItem.remove(i);
                    }
                    i =0;*/
                }
                if(KL.size()!= 0) {
                    KP.setKhrogaPackage(KL);
                    //KARIM 17/6/2016
                    int tempPrice=0;
                    for (KhrogaItem oneItem:KL) {
                        tempPrice+=Integer.parseInt(oneItem.getPrice());

                    }
                    KP.setPrice(""+tempPrice);

                    // 3shan el ordering 3mlt kda//end of karim's raz3
                    last.add(KP);
                    SummtionBudget = 0;
                }
            }



//Da hytshal 3shan n5li el data dynamically
           /* KhrogaItem KI = new KhrogaItem("4d7cd8f486cfa143c467d1a0", "Peking", "70", "Restaurant", "43 Rd. 250", "8.2", "12:00 PM to 12:00 AM", "Chinese Restaurant", "WiFi, No Smoking Area, Alcohol", "2016078", "http://i.imgur.com/pcQAn5l.png", "Maadi");
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

            KP.setKhrogaPackage(myListItem);
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
*/
           //Collections.shuffle(last);
            Collections.sort(last);





            packagesAdapter = new PackagesAdapter(getActivity(), last);
            packagesGridView = (GridView) rootView.findViewById(R.id.GridView_Packages);
            packagesGridView.setAdapter(packagesAdapter);

        }




}


        packagesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                KhrogaPackage selectedPackage = packagesAdapter.getItem(position);
                showDialogue(selectedPackage.getKhrogaPackage());

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

                //  view.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.DARKEN);

                return true;
            }
        });


        return rootView;
    }

    public void showDialogue(final ArrayList<KhrogaItem> selectedPackage) {


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
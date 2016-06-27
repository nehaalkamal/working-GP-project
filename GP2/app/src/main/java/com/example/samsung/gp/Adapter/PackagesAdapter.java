package com.example.samsung.gp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samsung.gp.Favourites;
import com.example.samsung.gp.Model.KhrogaItem;
import com.example.samsung.gp.Model.KhrogaPackage;
import com.example.samsung.gp.R;
import com.example.samsung.gp.helper.SessionManager;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by TOSHIBA on 2016-04-09.
 */
public class PackagesAdapter extends ArrayAdapter<KhrogaPackage> {
    private String activityTitle;
    private SessionManager session;


    // boolean array for storing
    //the state of each CheckBox (el checkboxes bethyes worldwide)
    boolean[] checkBoxState;


    public PackagesAdapter(Context context, List<KhrogaPackage> objects) {
        super(context, 0, objects);
        activityTitle="";
        //create the boolean array with
        //initial state as false
        checkBoxState=new boolean[objects.size()];


    }
    public PackagesAdapter(Context context, List<KhrogaPackage> objects,String activityTitle) { //7eta oop
        super(context, 0, objects);
        this.activityTitle = activityTitle;
        checkBoxState=new boolean[objects.size()];



    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final KhrogaPackage khrogapack = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.package_item, parent, false);
        }


        //inflate el layouts of package_item
        TextView packageTitle = (TextView) convertView.findViewById(R.id.txtTitle);
        TextView packagePrice = (TextView) convertView.findViewById(R.id.txtPrice);
        TextView packageRating = (TextView) convertView.findViewById(R.id.ratingBar);
        ImageView imgMix = (ImageView) convertView.findViewById(R.id.imgMix);
        ImageView imgMix2 = (ImageView) convertView.findViewById(R.id.imgMix2);
        ImageView imgMix3 = (ImageView) convertView.findViewById(R.id.imgMix3);


        final CheckBox packagebtnFavourite = (CheckBox) convertView.findViewById(R.id.imgFavourite);
        session = new SessionManager(getContext());

        if (!session.isLoggedIn()) {
            packagebtnFavourite.setVisibility(View.INVISIBLE);
        }

        //na2es el Miximage

        KhrogaItem kItem;
        String tempId = "";
        int tempPrice = 0;
        String tempTitle = "";
        double tempRating = 0.0;


        for (int i = 0; i < khrogapack.getKhrogaPackage().size(); i++) {
            kItem = khrogapack.getKhrogaPackage().get(i);
            tempPrice += Integer.parseInt(kItem.getPrice());
            tempId += kItem.getID() + kItem.getName();
            if (i < 2) {
                tempTitle += (kItem.getName() + "\n");
            }
            tempRating += Double.parseDouble(kItem.getRate());

        }

        if (khrogapack.getKhrogaPackage().size() >= 3)
            tempTitle += "and " + (khrogapack.getKhrogaPackage().size() - 2) + " more"; // -2 : for the 2 items displayed


        khrogapack.setPrice(String.valueOf(tempPrice));
        khrogapack.setID(tempId);
        khrogapack.setFavorited(false);
        khrogapack.setTitle(tempTitle);
        // khrogapack.setRating("" + (tempRating * (packageRating.getNumStars()) / (khrogapack.getKhrogaPackage().size() * 10)));

        khrogapack.setRating("" + new BigDecimal((tempRating / (khrogapack.getKhrogaPackage().size()))).setScale(1, RoundingMode.HALF_UP).doubleValue());// for rounding or approximation
        //khrogapack.setRating("" + (tempRating  / (khrogapack.getKhrogaPackage().size())));

        if (session.isLoggedIn() && FavDatabase.getFKhrogaPackagesList(getContext()) != null) {

            for (KhrogaPackage packFromDB : FavDatabase.getFKhrogaPackagesList(getContext())) { //make the already favorited display wih red heart
                if (packFromDB.getID().equals(khrogapack.getID())) {
                    khrogapack.setFavorited(true);
                    checkBoxState[position] = true;
                }
            }
        }

        //Han7ot el values b2a tzhr fel layout in textViews w kda
        packagePrice.setText(khrogapack.getPrice());
        packageTitle.setText(khrogapack.getTitle());
        packageRating.setText(khrogapack.getRating());
        packagebtnFavourite.setChecked(checkBoxState[position]);


        if(session.isLoggedIn()){
        packagebtnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (packagebtnFavourite.isChecked()) {
                    Toast.makeText(getContext(), "added to favourites", Toast.LENGTH_SHORT).show();
                    //7ot fl db :D
                    checkBoxState[position] = true;
                    khrogapack.setFavorited(true);

                    FavDatabase.saveSingleKhrogaPackage(khrogapack, getContext());
                    FavDatabase.updateBEfromSP(getContext());

                } else {
                    Toast.makeText(getContext(), "removed from favourites", Toast.LENGTH_SHORT).show();
                    //sheel ml db :(
                    checkBoxState[position] = false;
                    khrogapack.setFavorited(false);
                    FavDatabase.unsaveSingleKhrogaPackage(khrogapack, getContext());
                    FavDatabase.updateBEfromSP(getContext());
                    if (activityTitle.equals(getContext().getResources().getString(R.string.title_activity_Favourites))) {
                        ((Favourites) getContext()).autoRefresh();//((Login_Activity())mContext)
                    }


                }


            }
        });
    }

        //Mix images part
        if(khrogapack.getKhrogaPackage().size()==1){



            imgMix2.setVisibility(View.INVISIBLE);
            imgMix3.setVisibility(View.INVISIBLE);
            Picasso.with(getContext()).load(khrogapack.getKhrogaPackage().get(0).getImage()).into(imgMix);
            imgMix.setVisibility(View.VISIBLE);

        }
        else if (khrogapack.getKhrogaPackage().size()==2){


            Picasso.with(getContext()).load(khrogapack.getKhrogaPackage().get(0).getImage()).into(imgMix2);
            Picasso.with(getContext()).load(khrogapack.getKhrogaPackage().get(1).getImage()).into(imgMix3);
            imgMix.setVisibility(View.INVISIBLE);
            imgMix2.setVisibility(View.VISIBLE);
            imgMix3.setVisibility(View.VISIBLE);
        }
        else{
            Picasso.with(getContext()).load(khrogapack.getKhrogaPackage().get(0).getImage()).into(imgMix);
            Picasso.with(getContext()).load(khrogapack.getKhrogaPackage().get(1).getImage()).into(imgMix2);
            Picasso.with(getContext()).load(khrogapack.getKhrogaPackage().get(2).getImage()).into(imgMix3);

            imgMix.setVisibility(View.VISIBLE);
            imgMix2.setVisibility(View.VISIBLE);
            imgMix3.setVisibility(View.VISIBLE);

        }


        return convertView;

    }

}

package com.example.samsung.gp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samsung.gp.Model.KhrogaItem;
import com.example.samsung.gp.Model.KhrogaPackage;
import com.example.samsung.gp.R;

import java.util.List;

/**
 * Created by TOSHIBA on 2016-04-09.
 */
public class PackagesAdapter extends ArrayAdapter<KhrogaPackage> {


    public PackagesAdapter(Context context, List<KhrogaPackage> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        KhrogaPackage khrogapack = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.package_item, parent, false);
        }

        //inflate el layouts of package_item
        TextView packageTitle = (TextView) convertView.findViewById(R.id.txtTitle);
        TextView packagePrice = (TextView) convertView.findViewById(R.id.txtPrice);
        TextView packageRating = (TextView) convertView.findViewById(R.id.ratingBar);
        ImageView packageStar = (ImageView) convertView.findViewById(R.id.star);

        CheckBox packagebtnFavourite = (CheckBox) convertView.findViewById(R.id.imgFavourite);
        //na2es el Miximage

        KhrogaItem kItem;
        int tempPrice = 0;
        String tempTitle = "";
        double tempRating = 0.0;

        for (int i = 0; i < khrogapack.getKhrogaPackage().size(); i++) {
            kItem = khrogapack.getKhrogaPackage().get(i);
            tempPrice += Integer.parseInt(kItem.getPrice());
            if(i < 2){
            tempTitle += (kItem.getName() + "\n");}
            tempRating += Double.parseDouble(kItem.getRate());

        }

        if(khrogapack.getKhrogaPackage().size()>3)
            tempTitle +="and "+(khrogapack.getKhrogaPackage().size()-3)+" more";


        khrogapack.setPrice(String.valueOf(tempPrice));
        khrogapack.setTitle(tempTitle);
       // khrogapack.setRating("" + (tempRating * (packageRating.getNumStars()) / (khrogapack.getKhrogaPackage().size() * 10)));
        khrogapack.setRating("" + (tempRating  / (khrogapack.getKhrogaPackage().size())));

        //Han7ot el values b2a tzhr fel layout in textViews w kda
        packagePrice.setText(khrogapack.getPrice());
        packageTitle.setText(khrogapack.getTitle());
        packageRating.setText(khrogapack.getRating());
        if (khrogapack.isFavorited()) {

            packagebtnFavourite.setChecked(true);
        }
     /*   packagebtnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Hi",Toast.LENGTH_LONG).show();

            }
        });

*/
        return convertView;

    }
}

 /*      hast3ml de fl KhrogatpackageFragment
     void makeFavourite(View v) { //if button pressed
        Button btnFavourite = (Button) v.findViewById(R.id.imgFavourite);

        if (btnFavourite.getText().equals(R.string.btnFavText)) { // "=favourite"

            if (Database.getFMoviesList(getContext()) != null)
                FavouriteMovieList = Database.getFMoviesList(getContext());

            btnFavourite.setText(R.string.btn_text_unfavourite); //unfavourite
            btnFavourite.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.DARKEN);
            DetailFragment.selectedMovie.setIsfavourited(true);

            FavouriteMovieList.add(DetailFragment.selectedMovie);
            if (FavouriteMovieList != null)
                Database.saveFMoviesList(FavouriteMovieList, getContext());

        } else if (btnFavourite.getText().equals(R.string.btn_text_unfavourite)) { //"= unfav"

            btnFavourite.setText(R.string.btnFavText); //Mark as favourite
            btnFavourite.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);

            Database.clearFMovie(DetailFragment.selectedMovie, getContext());
            FavouriteMovieList = Database.getFMoviesList(getContext());

        }

    }*/

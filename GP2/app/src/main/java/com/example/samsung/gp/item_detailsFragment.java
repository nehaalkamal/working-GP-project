package com.example.samsung.gp;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.text.util.Linkify;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.samsung.gp.Model.KhrogaItem;
import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class item_detailsFragment extends Fragment {



    private TextView ItemName;
//    private TextView ItemPrice;
    private TextView ItemAddress;
    private TextView ItemRating;
    private TextView OpenHours;
    private TextView Cuisine;
    private TextView Features;
    private TextView Phone;
    private ImageView ItemImage;

  /*  String[] Items = {"boom boom boom", "boom boom boom", "boom boom boom"};
    String[] price = {"Menna Salah","Menna Salah","Menna Salah"};
    Integer[] image = {R.drawable.useravatar,R.drawable.useravatar,R.drawable.useravatar};*/


    public item_detailsFragment() {
    }

    /*  if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pop_up_listing,parent,false);
        }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView2 = inflater.inflate(R.layout.fragment_item_details, container, false);


       /* ListView listItem = (ListView) rootView2.findViewById(R.id.commentslist);
        CustomArrayAdapter myAdapter = new CustomArrayAdapter(getActivity(), Items);
     listItem.setAdapter(myAdapter);
*/

        ItemName= (TextView)rootView2.findViewById(R.id.txtmainname);
//        ItemPrice=(TextView) rootView2.findViewById(R.id.txtpriceno);
        ItemAddress=(TextView) rootView2.findViewById(R.id.txtLocation);
        ItemRating=(TextView) rootView2.findViewById(R.id.txtrate);
        OpenHours=(TextView) rootView2.findViewById(R.id.txtopenhoursin);
        Cuisine=(TextView) rootView2.findViewById(R.id.txtcuisine);
        Features=(TextView)rootView2.findViewById(R.id.txtfeaturesin);
        Phone=(TextView)rootView2.findViewById(R.id.txtphoneno);
        ItemImage=(ImageView)rootView2.findViewById(R.id.placelogo);



        ItemName.setText("Frozzy Twist");
//        ItemPrice.setText("18");
        ItemAddress.setText("15 Al-Bank St.Inside Saudian Company Bldgs.");
        ItemRating.setText("8.6");
        OpenHours.setText("1:00 pm to 1:00 am");
        Cuisine.setText("Desserts");
        Features.setText("Delivery, WiFi, Outdoor, No Smoking Area");
        Phone.setText("02 22876057");
        ItemImage.setBackgroundResource(R.drawable.rsz_frozzy_twist);
        return  rootView2;
    }

/*
        Intent intent = getActivity().getIntent();


        if (intent != null) {


   *//* class CustomArrayAdapter extends ArrayAdapter {

            KhrogaItem DetailedItem = (KhrogaItem) intent.getSerializableExtra("DetailedItem");
            ((TextView) rootView2.findViewById(R.id.txtmainname)).setText(DetailedItem.getName());
            ((TextView) rootView2.findViewById(R.id.txtphoneno)).setText(DetailedItem.getPhone());
            ((TextView) rootView2.findViewById(R.id.txtLocation)).setText(DetailedItem.getAddress());
            ((TextView) rootView2.findViewById(R.id.txtpriceno)).setText(DetailedItem.getPrice());
            ((TextView) rootView2.findViewById(R.id.txtpriceno)).setText(DetailedItem.getPrice());
            ((TextView) rootView2.findViewById(R.id.txtcuisine)).setText(DetailedItem.getCuisine());
            ((TextView) rootView2.findViewById(R.id.txtrate)).setText(DetailedItem.getRate());
            ((TextView) rootView2.findViewById(R.id.txtfeaturesin)).setText(DetailedItem.getFeatures());
           Drawable noImg =getActivity().getResources().getDrawable(R.drawable.no_image);
            Picasso.with(getActivity()).load(DetailedItem.getImage()).placeholder(noImg).into(((ImageView) rootView2.findViewById(R.id.placelogo)));




        }




        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.comments_list_item, parent,false);
            *//**//*
            * karim:*//**//*

           *//**//* if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comments_list_item, parent, false);
        }
            View rowView = convertView;*//**//*



            ImageView imageComment = (ImageView) rowView.findViewById(R.id.ImageComment); //imageComment Da feen fl xml?
            TextView txtName = (TextView) rowView.findViewById(R.id.usercomment);
            //final TextView txtComment = (TextView) rowView.findViewById(R.id.txtcomment);
            TextView txtPrice = (TextView) rowView.findViewById(R.id.username);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.userphoto);
            txtName.setText(values[position]);
            txtPrice.setText(price[position]);
            imageView.setImageResource(imageId[position]);


            *//**//*imageComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //String test = txtComment.getText().toString();
                    //txtName.setText(test);
                    txtPrice.setText(price[position]);
                    imageView.setImageResource(imageId[position]);

                }
            });*//**//*
            return rowView;*//*
        }





        return rootView2;
    }*/
}



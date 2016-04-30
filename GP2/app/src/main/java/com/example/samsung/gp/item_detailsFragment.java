package com.example.samsung.gp;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class item_detailsFragment extends Fragment {


    private TextView ItemName;
    private TextView ItemPrice;
    private TextView ItemAddress;
    private TextView ItemRating;
    private TextView OpenHours;
    private TextView Cuisine;
    private TextView Features;
    private TextView Phone;
    private ImageView ItemImage;

    String[] Items = {"boom boom boom", "boom boom boom", "boom boom boom"};
    String[] price = {"Menna Salah","Menna Salah","Menna Salah"};
    Integer[] image = {R.drawable.useravatar,R.drawable.useravatar,R.drawable.useravatar};

    public item_detailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView2 = inflater.inflate(R.layout.fragment_item_details, container, false);

        ListView listItem = (ListView) rootView2.findViewById(R.id.commentslist);
        CustomArrayAdapter myAdapter = new CustomArrayAdapter(getActivity(), Items);
        listItem.setAdapter(myAdapter);


        ItemName= (TextView)rootView2.findViewById(R.id.txtmainname);
        ItemPrice=(TextView) rootView2.findViewById(R.id.txtpriceno);
        ItemAddress=(TextView) rootView2.findViewById(R.id.txtLocation);
        ItemRating=(TextView) rootView2.findViewById(R.id.txtrate);
        OpenHours=(TextView) rootView2.findViewById(R.id.txtopenhoursin);
        Cuisine=(TextView) rootView2.findViewById(R.id.txtcuisine);
        Features=(TextView)rootView2.findViewById(R.id.txtfeaturesin);
        Phone=(TextView)rootView2.findViewById(R.id.txtphoneno);
        ItemImage=(ImageView)rootView2.findViewById(R.id.placelogo);



        ItemName.setText("Frozzy Twist");
        ItemPrice.setText("18");
        ItemAddress.setText("15 Al-Bank St. (Inside Saudian Company Bldgs.)");
        ItemRating.setText("8.6");
        OpenHours.setText("1:00 pm to 1:00 am");
        Cuisine.setText("Desserts");
        Features.setText("WiFi, Outdoor");
        Phone.setText("02 22876057");
        ItemImage.setBackgroundResource(R.drawable.rsz_frozzy_twist);
        return  rootView2;
    }



    class CustomArrayAdapter extends ArrayAdapter {

        Context con;
        String[] values;
        String[] Prices;
        Integer[] imageId;

        public CustomArrayAdapter(Context context, String[] items) {
            // TODO Auto-generated constructor stub
            super(context, R.layout.comments_list_item, items);
            con = context;
            values = items;
            imageId = image;
            Prices = price;

        }



        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.comments_list_item, parent,false);
            /*
            * karim:*/

           /* if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comments_list_item, parent, false);
        }
            View rowView = convertView;*/



            ImageView imageComment = (ImageView) rowView.findViewById(R.id.ImageComment); //imageComment Da feen fl xml?
            TextView txtName = (TextView) rowView.findViewById(R.id.usercomment);
            //final TextView txtComment = (TextView) rowView.findViewById(R.id.txtcomment);
            TextView txtPrice = (TextView) rowView.findViewById(R.id.username);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.userphoto);
            txtName.setText(values[position]);
            txtPrice.setText(price[position]);
            imageView.setImageResource(imageId[position]);


            /*imageComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //String test = txtComment.getText().toString();
                    //txtName.setText(test);
                    txtPrice.setText(price[position]);
                    imageView.setImageResource(imageId[position]);

                }
            });*/
            return rowView;
        }

    }


}
package com.example.samsung.gp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samsung.gp.Model.KhrogaItem;
import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class item_detailsFragment extends Fragment {

    public item_detailsFragment() {
    }

    /*  if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pop_up_listing,parent,false);
        }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView2 = inflater.inflate(R.layout.fragment_item_details, container, false);

        Intent intent = getActivity().getIntent();

        if (intent != null) {

            KhrogaItem DetailedItem = (KhrogaItem) intent.getSerializableExtra("DetailedItem");
            ((TextView) rootView2.findViewById(R.id.txtmainname)).setText(DetailedItem.getName());
            ((TextView) rootView2.findViewById(R.id.txtphoneno)).setText(DetailedItem.getPhone());
            ((TextView) rootView2.findViewById(R.id.txtLocation)).setText(DetailedItem.getAddress());
            //          ((TextView) rootView2.findViewById(R.id.txtpriceno)).setText(DetailedItem.getPrice());
//            ((TextView) rootView2.findViewById(R.id.txtpriceno)).setText(DetailedItem.getPrice());
            ((TextView) rootView2.findViewById(R.id.txtcuisine)).setText(DetailedItem.getCuisine());
            ((TextView) rootView2.findViewById(R.id.txtrate)).setText(DetailedItem.getRate());
            ((TextView) rootView2.findViewById(R.id.txtfeaturesin)).setText(DetailedItem.getFeatures());

            Picasso.with(getActivity()).load(DetailedItem.getImage()).placeholder(R.drawable.no_image).into(((ImageView) rootView2.findViewById(R.id.placelogo)));



        }

        return rootView2;
    }
}


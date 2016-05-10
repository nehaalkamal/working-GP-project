package com.example.samsung.gp.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samsung.gp.Model.KhrogaItem;
import com.example.samsung.gp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Hussein on 4/27/2016.
 */
public class listingAdapter extends ArrayAdapter<KhrogaItem> {

    public listingAdapter(Context context, ArrayList<KhrogaItem> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        KhrogaItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pop_up_listing,parent,false);
        }


            ((TextView) convertView.findViewById(R.id.name)).setText(item.getName());
            ((TextView) convertView.findViewById(R.id.price)).setText(item.getPrice());
            Drawable noImg =getContext().getResources().getDrawable(R.drawable.no_image);
            Picasso.with(getContext()).load(item.getImage()).placeholder(noImg).into(((ImageView) convertView.findViewById(R.id.image)));




        return convertView;
    }
}

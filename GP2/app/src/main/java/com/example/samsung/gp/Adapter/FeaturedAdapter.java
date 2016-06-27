package com.example.samsung.gp.Adapter;

import android.content.Context;
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
 * Created by TOSHIBA on 2016-06-15.
 */
public class FeaturedAdapter extends ArrayAdapter<KhrogaItem> {

    public FeaturedAdapter(Context context, ArrayList<KhrogaItem> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        KhrogaItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.featured_item,parent,false);
        }



        ((TextView) convertView.findViewById(R.id.title)).setText(item.getName());



        Picasso.with(getContext()).load(item.getBanner()).placeholder(R.drawable.no_image).into(((ImageView) convertView.findViewById(R.id.banner_image)));


        return convertView;
    }
}

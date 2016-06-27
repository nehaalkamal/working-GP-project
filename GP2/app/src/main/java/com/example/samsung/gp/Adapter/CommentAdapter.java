package com.example.samsung.gp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samsung.gp.Model.CommentItem;
import com.example.samsung.gp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Hussein on 6/3/2016.
 */
public class CommentAdapter extends ArrayAdapter<CommentItem>{

    Context con;
    ArrayList<CommentItem> values;


    public CommentAdapter(Context context, ArrayList<CommentItem> items) {
        // TODO Auto-generated constructor stub
        super(context, R.layout.comments_list_item, items);
        con = context;
        values = items;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.comments_list_item, parent, false);
        TextView txtName = (TextView) rowView.findViewById(R.id.username);
        TextView txtrate = (TextView) rowView.findViewById(R.id.rate);
        ImageView star = (ImageView) rowView.findViewById(R.id.starlogocomment);
        TextView txtcomment = (TextView) rowView.findViewById(R.id.usercomment);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.userphoto);
        txtName.setText(values.get(position).getName().toString());
        if(values.get(position).getrate().equals("-")) {
            txtrate.setText(values.get(position).getrate().toString());
            txtrate.setVisibility(View.GONE);
            star.setVisibility(View.GONE);
        }else
        {
            txtrate.setText(values.get(position).getrate().toString());
        }

        txtcomment.setText(values.get(position).getTxtComment().toString());
        Picasso.with(getContext()).load(values.get(position).getUserImage()).placeholder(R.drawable.no_image).into(((ImageView) rowView.findViewById(R.id.userphoto)));
        return rowView;
    }
}

package com.example.samsung.gp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.samsung.gp.FeaturedFragment;
import com.example.samsung.gp.SearchFragment;
import com.example.samsung.gp.core_screenFragment;

/**
 * Created by TOSHIBA on 2016-06-13.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){

            case 0:{
                return new core_screenFragment();}

            case 1:{
                return new FeaturedFragment();}
            case 2:{
                return new SearchFragment();}

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3; //number of tabs
    }
}

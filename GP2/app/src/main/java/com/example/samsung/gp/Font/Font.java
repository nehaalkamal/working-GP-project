package com.example.samsung.gp.Font;

import android.app.Application;

import com.example.samsung.gp.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by TOSHIBA on 2016-05-08.
 */
public class Font extends Application {
        @Override
        public void onCreate() {
            super.onCreate();
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/Bariol_Regular.otf")
                            .setFontAttrId(R.attr.fontPath)
                            .build()
            );
        }

}

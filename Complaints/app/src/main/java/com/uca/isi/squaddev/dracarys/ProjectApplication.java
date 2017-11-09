package com.uca.isi.squaddev.dracarys;

/**
 * Created by ericb on 09/11/2017.
 */

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tumblr.remember.Remember;

public class ProjectApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        Remember.init(getApplicationContext(), "xd.myapplication");
    }
}
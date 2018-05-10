package com.lwy.dbindingview.app;

import android.app.Application;

/**
 * Created by lwy on 2018/4/23.
 */

public class MyApplication extends Application {

    private static MyApplication sMyApplication;

    public static MyApplication getMyApplication() {
        return sMyApplication;
    }

    @Override
    public void onCreate() {
        sMyApplication = this;
        super.onCreate();
    }
}

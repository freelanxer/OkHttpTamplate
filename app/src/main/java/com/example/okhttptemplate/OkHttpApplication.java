package com.example.okhttptemplate;

import android.app.Application;

import com.example.okhttptemplate.network.ApiHelper;

public class OkHttpApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApiHelper.init(getApplicationContext());
    }
}

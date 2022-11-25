package com.example.okhttptemplate.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonHelper {
    public static Gson createGson(){
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")//c# datetime format
                .create();
    }
}

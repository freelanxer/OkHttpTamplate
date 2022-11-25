package com.example.okhttptemplate.utils;

import android.util.Log;

import com.example.okhttptemplate.BuildConfig;

public class Constant {
    public static final String TAG = "OkHttpTemplate";
    public static final boolean DEBUG = BuildConfig.BUILD_TYPE.contentEquals("debug");

    public static void logD(String msg) {
        if (!DEBUG)
            return;
        String info = getCallerInfo();
        Log.d(TAG,  msg + " (" + info + ")");
    }

    public static void logE(String msg) {
        if (!DEBUG)
            return;
        String info = getCallerInfo();
        Log.e(TAG,  msg + " (" + info + ")");
    }

    public static void logI(String msg) {
        if (!DEBUG)
            return;
        String info = getCallerInfo();
        Log.i(TAG,  msg + " (" + info + ")");
    }

    public static void logW(String msg) {
        if (!DEBUG)
            return;
        String info = getCallerInfo();
        Log.w(TAG,  msg + " (" + info + ")");
    }

    public static String getCallerInfo() {
        String clsName = "";
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTraceElements[4];
        clsName = stackTraceElement.getFileName();
        int lineNum = stackTraceElement.getLineNumber();
        clsName += ":" + lineNum;
        return clsName;
    }
}

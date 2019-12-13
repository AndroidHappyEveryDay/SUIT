package com.example.five.utils;

import android.util.Log;

import com.example.five.BuildConfig;


public class L {

    private static final String TAG = "Guzzi";

    private static boolean sDebug = BuildConfig.DEBUG;

    public static void d(String msg , Object... args){

        if (!sDebug){
            return;
        }
        Log.d(TAG, String.format(msg,args));
    }

    public static void d() {
    }
}

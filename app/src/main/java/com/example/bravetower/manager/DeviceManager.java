package com.example.bravetower.manager;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class DeviceManager {

    public static int width;
    public static int height;

    public static void initDevice(Activity activity){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);

        width = metrics.widthPixels;
        height = metrics.heightPixels;
    }
}

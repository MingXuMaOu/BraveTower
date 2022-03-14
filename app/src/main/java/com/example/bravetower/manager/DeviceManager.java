package com.example.bravetower.manager;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class DeviceManager {

    public static int width;
    public static int height;

    //宽为15格，长为32格
    private static int widthNum = 15;
    private static int heightNum = 32;
    public static int widthSize;
    public static int heightSize;

    public static void initDevice(Activity activity){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);

        width = metrics.widthPixels;
        height = metrics.heightPixels;

        widthSize = width / widthNum;
//        heightSize = height / heightNum;
        heightSize = widthSize;
    }
}

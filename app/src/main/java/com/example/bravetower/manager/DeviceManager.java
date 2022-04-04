package com.example.bravetower.manager;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import java.lang.reflect.Field;

public class DeviceManager {

    public static int width;
    public static int height;

    //宽为15格，长为32格
    public static int widthNum = 15;
    public static int heightNum = 32;
    public static int widthSize;
    public static int heightSize;
    public static int statusBarHeight;

    public static void initDevice(Activity activity){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);

        width = metrics.widthPixels;
        height = metrics.heightPixels;

        widthSize = width / widthNum;
//        heightSize = height / heightNum;
        heightSize = widthSize;
        statusBarHeight = getStatusBarHeight(activity);
    }

    private static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}

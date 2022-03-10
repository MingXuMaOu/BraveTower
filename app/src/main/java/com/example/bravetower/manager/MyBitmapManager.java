package com.example.bravetower.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import com.example.bravetower.R;

public class MyBitmapManager extends View {

    private static Bitmap bitmapMtLoadingbg;  //加载曾视图背景图

    public MyBitmapManager(Context context) {
        super(context);
    }

    public void initBitmap(){
        bitmapMtLoadingbg = ((BitmapDrawable) getResources().getDrawable(R.drawable.mtlodingbg)).getBitmap();
    }


    public static Bitmap getBitmapMtLoadingbg() {
        return bitmapMtLoadingbg;
    }

    public static void setBitmapMtLoadingbg(Bitmap bitmapMtLoadingbg) {
        MyBitmapManager.bitmapMtLoadingbg = bitmapMtLoadingbg;
    }
}

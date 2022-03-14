package com.example.bravetower.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import com.example.bravetower.R;

public class MyBitmapManager extends View {

    private static Bitmap bitmapMtLoadingbg;  //加载视图背景图
    private static Bitmap bitmapImgBg;        //加载游戏背景图
    private static Bitmap bitmapGoods;
    private static int bgWidthNum = 24;
    private  static int bgHeightNum = 16;
    private static int bgWidthSize;
    private static int bgHeightSize;
    private static Bitmap bitmapNpcImg;       //得到NPC图片
    private static Bitmap bitmapActorImg;     //得到玩家图片

    public MyBitmapManager(Context context) {
        super(context);
    }

    public void initBitmap(){
        bitmapMtLoadingbg = ((BitmapDrawable) getResources().getDrawable(R.drawable.mtlodingbg)).getBitmap();
        bitmapImgBg = ((BitmapDrawable) getResources().getDrawable(R.drawable.imgall)).getBitmap();
        bitmapGoods = ((BitmapDrawable) getResources().getDrawable(R.drawable.goods)).getBitmap();
        bitmapNpcImg = ((BitmapDrawable) getResources().getDrawable(R.drawable.npcimg)).getBitmap();
        bitmapActorImg = ((BitmapDrawable) getResources().getDrawable(R.drawable.myactor)).getBitmap();
        initBitmapSize();
    }

    private void initBitmapSize(){
        bgWidthSize = bitmapImgBg.getWidth() / bgWidthNum;
        bgHeightSize = bitmapImgBg.getHeight() / bgHeightNum;
    }


    public static Bitmap getBitmapMtLoadingbg() {
        return bitmapMtLoadingbg;
    }

    public static void setBitmapMtLoadingbg(Bitmap bitmapMtLoadingbg) {
        MyBitmapManager.bitmapMtLoadingbg = bitmapMtLoadingbg;

    }

    public static Bitmap getBitmapImgBg() {
        return bitmapImgBg;
    }

    public static void setBitmapImgBg(Bitmap bitmapImgBg) {
        MyBitmapManager.bitmapImgBg = bitmapImgBg;
    }

    public static int getBgWidthSize() {
        return bgWidthSize;
    }

    public static void setBgWidthSize(int bgWidthSize) {
        MyBitmapManager.bgWidthSize = bgWidthSize;
    }

    public static int getBgHeightSize() {
        return bgHeightSize;
    }

    public static void setBgHeightSize(int bgHeightSize) {
        MyBitmapManager.bgHeightSize = bgHeightSize;
    }

    public static Bitmap getBitmapGoods() {
        return bitmapGoods;
    }

    public static void setBitmapGoods(Bitmap bitmapGoods) {
        MyBitmapManager.bitmapGoods = bitmapGoods;
    }

    public static Bitmap getBitmapNpcImg() {
        return bitmapNpcImg;
    }

    public static void setBitmapNpcImg(Bitmap bitmapNpcImg) {
        MyBitmapManager.bitmapNpcImg = bitmapNpcImg;
    }

    public static Bitmap getBitmapActorImg() {
        return bitmapActorImg;
    }

    public static void setBitmapActorImg(Bitmap bitmapActorImg) {
        MyBitmapManager.bitmapActorImg = bitmapActorImg;
    }
}

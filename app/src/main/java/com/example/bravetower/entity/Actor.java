package com.example.bravetower.entity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.example.bravetower.R;
import com.example.bravetower.manager.DeviceManager;
import com.example.bravetower.manager.DrawImgArr;
import com.example.bravetower.manager.MyBitmapManager;

public class Actor implements Runnable{

    private int x;      //x坐标
    private int y;      //y坐标
    private int currentIndex;
    private int fx;     //朝向
    private Bitmap bitmap;

    private static Actor actor;

    private Actor(Context context){
        bitmap = MyBitmapManager.getBitmapActorImg();
        new Thread(this).start();
    }

    public static Actor getActor(Context context){
        if(actor == null){
            actor = new Actor(context);
        }
        return actor;
    }

    public void drawActor(Canvas canvas){
        Rect src = new Rect();
        src.left = currentIndex * MyBitmapManager.getBgWidthSize();
        src.right = (currentIndex + 1) * MyBitmapManager.getBgWidthSize();
        src.top = fx * MyBitmapManager.getBgHeightSize();
        src.bottom = (fx + 1) * MyBitmapManager.getBgHeightSize();
        Rect dst = new Rect();
        dst.left = x;
        dst.right = x + DeviceManager.widthSize;
        dst.top = y;
        dst.bottom = y + DeviceManager.heightSize;
        canvas.drawBitmap(bitmap,src,dst,null);
    }

    @Override
    public void run() {
        while (true){
            try{
                Thread.sleep(300);
                currentIndex++;
                if(currentIndex > 2){
                    currentIndex = 0;
                }
            }catch (Exception e){
                Log.i(Actor.class.toString(),e.getMessage());
            }
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int getFx() {
        return fx;
    }

    public void setFx(int fx) {
        this.fx = fx;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}

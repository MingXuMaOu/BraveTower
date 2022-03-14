package com.example.bravetower.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.bravetower.manager.DeviceManager;
import com.example.bravetower.manager.ImgArrManager;
import com.example.bravetower.manager.MyBitmapManager;

public class Npc implements Runnable{

    private Bitmap bitmap;
    private static int[][] npcImageArr;
    private int currentIndex;
    private static Npc npc;

    private Npc(){
        bitmap = MyBitmapManager.getBitmapNpcImg();
        new Thread(this).start();
    }

    public static Npc getNpc(){
        if(npc == null){
            npc = new Npc();
        }
        npcImageArr = ImgArrManager.npcImageArr;
        return npc;
    }

    public void drawNpc(Canvas canvas){
        if(npcImageArr != null){
            for (int row = 0;row < npcImageArr.length;row++){
                for (int column = 0; column < npcImageArr[row].length;column++){
                    int imgIndex = npcImageArr[row][column];
                    if(imgIndex != 0){
                        int srow = imgIndex / 3;
                        drawImg(canvas,bitmap,srow,currentIndex,row,column);
                    }
                }
            }
        }
    }
    private void drawImg(Canvas canvas,Bitmap bitmap,int srow,int scolumn,int row,int column){
        Rect src = new Rect();
        src.left = scolumn * MyBitmapManager.getBgWidthSize();
        src.right = (scolumn + 1) * MyBitmapManager.getBgWidthSize();
        src.top = srow * MyBitmapManager.getBgHeightSize();
        src.bottom = (srow + 1) * MyBitmapManager.getBgHeightSize();

        Rect dst = new Rect();
        dst.left = column * DeviceManager.widthSize;
        dst.right = (column + 1) * DeviceManager.widthSize;
        dst.top = row * DeviceManager.heightSize;
        dst.bottom = (row + 1) * DeviceManager.heightSize;

        canvas.drawBitmap(bitmap,src,dst,null);
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try{
                Thread.sleep(300);
                currentIndex++;
                if(currentIndex == 3){
                    currentIndex = 0;
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}

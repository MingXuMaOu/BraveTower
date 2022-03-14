package com.example.bravetower.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.bravetower.entity.Actor;
import com.example.bravetower.entity.Npc;
import com.example.bravetower.manager.DeviceManager;
import com.example.bravetower.manager.ImgArrManager;
import com.example.bravetower.manager.MyBitmapManager;

public class GameView extends View implements Runnable{

    private Paint paint;

    private Bitmap bitmapBg;
    private Bitmap bitmapGoods;

    private Actor actor;
    private Npc npc;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        bitmapBg = MyBitmapManager.getBitmapImgBg();
        bitmapGoods = MyBitmapManager.getBitmapGoods();

        npc = Npc.getNpc();
        actor = Actor.getActor(context);
        new Thread(this).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制底层背景
        drawImageByArr(canvas,bitmapBg, ImgArrManager.bgImageArr,24);
        //绘制半透明层覆盖背景
        paint.setAlpha(50);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0,0,DeviceManager.width,DeviceManager.height,paint);
        //绘制地形背景
        drawImageByArr(canvas,bitmapBg,ImgArrManager.bg2ImageArr,24);
        //绘制障碍背景
        drawImageByArr(canvas,bitmapBg,ImgArrManager.zawImgaeArr,24);
        //绘制楼梯
        drawImageByArr(canvas,bitmapBg,ImgArrManager.stairImageArr,24);
        //绘制物品
        drawImageByArr(canvas,bitmapGoods,ImgArrManager.goodsImageArr,4);
        actor.drawActor(canvas);

        npc.drawNpc(canvas);
    }

    public void drawImageByArr(Canvas canvas, Bitmap image, int[][] arrImg,int rowSize) {
        if(arrImg!=null){
            for (int row = 0; row < arrImg.length; row++) {
                for (int column = 0; column < arrImg[row].length; column++) {
                    int imgIndex = arrImg[row][column];
                    if(imgIndex!=0){
                        //获得本整形元素在图片上的行/列
                        int srow= imgIndex/rowSize;
                        int scolumn= imgIndex%rowSize;

                        drawImg(canvas,image,srow,scolumn,row,column);
                    }
                }
            }
        }
    }
    //绘制图像
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
                Thread.sleep(100);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
            postInvalidate();
        }
    }
}

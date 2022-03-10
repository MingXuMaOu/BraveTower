package com.example.bravetower.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.bravetower.manager.MyBitmapManager;

public class LoadingView extends ConstraintLayout {

    private Bitmap bitmapbg;


    public LoadingView(Context context) {
        super(context);

        bitmapbg = MyBitmapManager.getBitmapMtLoadingbg();

        //添加背景视图
        initView(context);
    }

    private void initView(Context context){
        View view = new View(context){
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
//                canvas.drawBitmap(bitmapbg,0,0,null);
            }
        };

        addView(view);
    }
}

package com.example.bravetower.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.bravetower.activity.LoadingActivity;
import com.example.bravetower.manager.MyBitmapManager;

public class LoadingView extends View {

    private Bitmap bitmapbg;
    private Paint paint;
    private int alpha;


    public LoadingView(Context context, AttributeSet attrs) {
        super(context,attrs);
        //初始化数据
        initData();
    }

    private void initData(){
        bitmapbg = MyBitmapManager.getBitmapMtLoadingbg();
        alpha = 0;
        paint = new Paint();
        paint.setColor(Color.LTGRAY);
        paint.setTextSize(36);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        alpha += 10;
        paint.setAlpha(alpha);
        canvas.drawBitmap(bitmapbg,0,0,paint);
    }
}

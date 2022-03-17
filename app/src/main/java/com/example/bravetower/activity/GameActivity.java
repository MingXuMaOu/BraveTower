package com.example.bravetower.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bravetower.R;
import com.example.bravetower.entity.Actor;
import com.example.bravetower.manager.DeviceManager;
import com.example.bravetower.manager.ImgArrManager;

public class GameActivity extends BaseActivity{
    private static final String TAG = "GameActivity";

    public Handler handler = new Handler(Looper.myLooper()){

    };

    private ImageView upBt;
    private ImageView downBt;
    private ImageView leftBt;
    private ImageView rightBt;

    private Actor actor;

    private OnClickBtn onClickBtn = new OnClickBtn();


    public static Intent newIntent(Activity activity){
        Intent intent = new Intent(activity,GameActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actor = Actor.getActor(this);
        //加载本层信息
        ImgArrManager.getZAWInfo();
        actor.setX(ImgArrManager.acX);
        actor.setY(ImgArrManager.acY);

        setContentView(R.layout.activity_game);

        initData();

    }

    private void initData(){
        upBt = findViewById(R.id.control_up);
        downBt = findViewById(R.id.control_down);
        leftBt = findViewById(R.id.control_left);
        rightBt = findViewById(R.id.control_right);

        OnTouch onTouch = new OnTouch();

        upBt.setOnTouchListener(onTouch);
        downBt.setOnTouchListener(onTouch);
        leftBt.setOnTouchListener(onTouch);
        rightBt.setOnTouchListener(onTouch);
    }



    class OnTouch implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                onClickBtn.v = v;
                handler.post(onClickBtn);
            }else if(event.getAction() == MotionEvent.ACTION_UP){
                handler.removeCallbacks(onClickBtn);
            }

            return true;
        }
    }

    class OnClickBtn implements Runnable{
        int ax = 0;
        int ay = 0;
        View v = null;
        @Override
        public void run() {
            actorCheckAndMove();
            handler.postDelayed(onClickBtn,80);

        }
        private void actorCheckAndMove(){
            int result = 0;
            if(v == upBt){
                ax = 0;
                ay = -DeviceManager.heightSize / 4;
                actor.setFx(3);
                result = checkBound(ax,ay);
            }else if(v == downBt){
                ax = 0;
                ay = DeviceManager.heightSize / 4;
                actor.setFx(0);
                result = checkBound(ax,ay);
            }else if(v == leftBt){
                ax = - DeviceManager.widthSize / 4;
                ay = 0;
                actor.setFx(1);
                result = checkBound(ax,ay);
            }else if(v == rightBt){
                ax = DeviceManager.widthSize / 4;
                ay = 0;
                actor.setFx(2);
                result = checkBound(ax,ay);
            }
            checkResult(result,ax,ay);
        }
        private int checkBound(int ax,int ay){
            if(ImgArrManager.zawImgaeArr == null || checkWall(ImgArrManager.zawImgaeArr,ax,ay)){
                return 0;
            }else {
                return 1;
            }
        }


        private void checkResult(int result,int ax,int ay){
            if(result == 0) {
                actor.setX(actor.getX() + ax);
                actor.setY(actor.getY() + ay);
            }else {
            }
        }
        //判断是否遇到障碍物
        private boolean checkWall(int[][] wall,int ax,int ay){
            int x = actor.getX();
            int y = actor.getY();
            for (int row = 0; row < wall.length; row++){
                for (int column = 0; column < wall[row].length;column++){
                    int value = wall[row][column];
                    if(value !=0){
                        //左方向判断
                        boolean b = (y >= row * DeviceManager.widthSize && y < (row + 1) * DeviceManager.widthSize) || (y + DeviceManager.widthSize > row * DeviceManager.widthSize && y + DeviceManager.widthSize <= (row + 1) * DeviceManager.widthSize);
                        if(actor.getFx() == 1){
                            if((x == (column + 1 ) * DeviceManager.widthSize) && b){
                                return false;
                            }
                        }
                        //右方向判断
                        else if(actor.getFx() == 2){
                            if((x == (column - 1)  * DeviceManager.widthSize) && b){
                                return false;
                            }
                        }
                        //上方向判断
                        else {
                            boolean b1 = (x >= column * DeviceManager.widthSize && x < (column + 1) * DeviceManager.widthSize) || (x + DeviceManager.widthSize > column * DeviceManager.widthSize && x + DeviceManager.widthSize <= (column + 1) * DeviceManager.widthSize);
                            if(actor.getFx() == 3){
                                if((y == (row + 1) * DeviceManager.widthSize) && b1){
                                    return false;
                                }
                            }
                            //下方向判断
                            else if(actor.getFx() == 0){
                                if((y == (row - 1) * DeviceManager.widthSize) && b1){
                                    return false;
                                }
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}
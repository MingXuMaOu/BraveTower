package com.example.bravetower.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bravetower.R;
import com.example.bravetower.entity.Actor;
import com.example.bravetower.manager.ImgArrManager;

public class GameActivity extends BaseActivity{

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
            return false;
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

        }
    }
}

package com.example.bravetower.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bravetower.R;
import com.example.bravetower.view.LoadingView;

import net.frakbot.jumpingbeans.JumpingBeans;

public class LoadingActivity extends BaseActivity {

    private View loadView;
    private ProgressBar progressBar;
    private TextView loadText;
    private int progress;

    public Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {

            super.handleMessage(msg);
            if(msg.arg1 == 88){
                finishLoad();
            }
        }
    };

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            if(progress < 100){
                handler.postDelayed(runnable,50);
            }else if(progress == 100){
                Message message = handler.obtainMessage();
                message.arg1 = 88;
                handler.sendMessage(message);
            }else {
                handler.removeCallbacks(runnable);
            }

            loadView.postInvalidate();
            progress += 5;
            progressBar.setProgress(progress);

        }
    };

    public static Intent newIntent(Activity activity){
        Intent intent = new Intent(activity,LoadingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //定义加载页面视图
        setContentView(R.layout.activity_loading);


        loadView = findViewById(R.id.load_view);
        progressBar = findViewById(R.id.load_progress);
        loadText = findViewById(R.id.load_text);

        JumpingBeans.with(loadText)
                .makeTextJump(0,loadText.getText().length())
                .setIsWave(true)
                .setLoopDuration(1000)
                .build();

        handler.postDelayed(runnable,100);

    }

    //完成加载
    public void finishLoad(){
        Intent intent = GameActivity.newIntent(this);
        startActivity(intent);
        finish();
    }
}
package com.example.bravetower.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.bravetower.R;

public class GameActivity extends AppCompatActivity {

    public static Handler handler;

    public static Intent newIntent(Activity activity){
        Intent intent = new Intent(activity,GameActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        handler.postDelayed(runnable,3000);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("开始发送");
            Message message = handler.obtainMessage();
            message.arg1 = 88;
            handler.sendMessage(message);
        }
    };
}
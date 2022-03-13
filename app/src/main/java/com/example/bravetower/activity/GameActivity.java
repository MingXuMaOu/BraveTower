package com.example.bravetower.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bravetower.R;
import com.example.bravetower.manager.ImgArrManager;

public class GameActivity extends BaseActivity implements View.OnClickListener {

    private ImageView upBt;
    private ImageView downBt;
    private ImageView leftBt;
    private ImageView rightBt;


    public static Intent newIntent(Activity activity){
        Intent intent = new Intent(activity,GameActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //加载本层信息
        ImgArrManager.getZAWInfo();

        setContentView(R.layout.activity_game);

        initData();

    }

    private void initData(){
        upBt = findViewById(R.id.control_up);
        downBt = findViewById(R.id.control_down);
        leftBt = findViewById(R.id.control_left);
        rightBt = findViewById(R.id.control_right);

        upBt.setOnClickListener(this);
        downBt.setOnClickListener(this);
        leftBt.setOnClickListener(this);
        rightBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == upBt){
            Toast.makeText(this, "Up", Toast.LENGTH_SHORT).show();
        }else if(v == downBt){
            Toast.makeText(this, "down", Toast.LENGTH_SHORT).show();
        }else if(v == leftBt){
            Toast.makeText(this, "left", Toast.LENGTH_SHORT).show();
        }else if(v == rightBt){
            Toast.makeText(this, "right", Toast.LENGTH_SHORT).show();
        }

    }
}
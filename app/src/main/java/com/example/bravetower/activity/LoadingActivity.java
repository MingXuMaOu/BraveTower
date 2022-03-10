
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

import com.example.bravetower.R;
import com.example.bravetower.view.LoadingView;

public class LoadingActivity extends BaseActivity {
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            System.out.println("测试");
            super.handleMessage(msg);
            if(msg.arg1 == 88){
                finishLoad();
            }
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
        LoadingView layout = new LoadingView(this);
        setContentView(layout);

        GameActivity.handler = this.handler;
    }

    //完成加载
    public void finishLoad(){
        Intent intent = GameActivity.newIntent(this);
        startActivity(intent);


    }
}
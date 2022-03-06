package com.example.bravetower.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.bravetower.R;
import com.example.bravetower.activity.BaseActivity;
import com.example.bravetower.manager.DeviceManager;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayoutCompat mMenuLinear;
    private TextView mSoloText;
    private TextView mDoubleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //获取设备信息
        DeviceManager.initDevice(this);
        //初始化组件
        initModule();

    }

    private void initModule(){
        mMenuLinear = findViewById(R.id.register_menu);
        mSoloText = findViewById(R.id.solo_model);
        mDoubleText = findViewById(R.id.double_model);

        mMenuLinear.setOnClickListener(this);
        mSoloText.setOnClickListener(this);
        mDoubleText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mMenuLinear){
            Toast.makeText(this, "菜单", Toast.LENGTH_SHORT).show();
        }else if(v == mSoloText){
            Toast.makeText(this, "单人", Toast.LENGTH_SHORT).show();
        }else if(v == mDoubleText){
            Toast.makeText(this, "双人", Toast.LENGTH_SHORT).show();
        }

    }
}
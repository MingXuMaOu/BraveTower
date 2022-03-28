package com.example.bravetower.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.bravetower.R;
import com.example.bravetower.dialog.ProgressDialogUtils;
import com.example.bravetower.dialog.TalkDialog;
import com.example.bravetower.dialog.TalkDialogUtils;
import com.example.bravetower.entity.Actor;
import com.example.bravetower.manager.DeviceManager;
import com.example.bravetower.manager.ImgArrManager;

public class GameActivity extends BaseActivity{
    private static final String TAG = "GameActivity";

    public Handler handler = new Handler(Looper.myLooper()){

    };

    private TextView actorLeve;
    private TextView actorMoney;
    private TextView actorAttack;
    private TextView actorDefense;
    private TextView actorHeart;
    private TextView yellowKey;
    private TextView blueKey;
    private TextView redKey;
    private TextView leftDialogTx;  //对话框
    private TextView rightDialogTx;

    private ImageView upBt;
    private ImageView downBt;
    private ImageView leftBt;
    private ImageView rightBt;
    private ImageView confirmBt;

    private Actor actor;
    private int indexRow;
    private int indexColumn;
    private int indexValue;

    private OnClickBtn onClickBtn = new OnClickBtn();
    private int result;


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

        //初始化组件
        initData();
        //初始化角色数据
        initActor();

    }

    private void initData(){
        actorLeve = findViewById(R.id.actor_level);
        actorMoney = findViewById(R.id.actor_money);
        actorAttack = findViewById(R.id.actor_attack);
        actorDefense = findViewById(R.id.actor_defense);
        actorHeart = findViewById(R.id.actor_heart);
        yellowKey = findViewById(R.id.yellow_key);
        blueKey = findViewById(R.id.blue_key);
        redKey = findViewById(R.id.red_key);
        leftDialogTx = findViewById(R.id.left_dialog_tx);
        rightDialogTx = findViewById(R.id.right_dialog_tx);

        upBt = findViewById(R.id.control_up);
        downBt = findViewById(R.id.control_down);
        leftBt = findViewById(R.id.control_left);
        rightBt = findViewById(R.id.control_right);
        confirmBt = findViewById(R.id.confirm_bt);

        OnClick onClick = new OnClick();
        OnTouch onTouch = new OnTouch();

        upBt.setOnTouchListener(onTouch);
        downBt.setOnTouchListener(onTouch);
        leftBt.setOnTouchListener(onTouch);
        rightBt.setOnTouchListener(onTouch);
        confirmBt.setOnClickListener(onClick);
    }

    private void initActor(){
        actorLeve.setText("lv " + actor.getLevel());
        actorMoney.setText(actor.getMoney() + "");
        actorAttack.setText(actor.getAttack() + "");
        actorDefense.setText(actor.getDefense() + "");
        actorHeart.setText(actor.getHp() + "");
        yellowKey.setText(actor.getYellowKey() + "");
        blueKey.setText(actor.getBlueKey() + "");
        redKey.setText(actor.getRedKey() + "");

    }
    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v == confirmBt){
                if(result == 2) {
                    TalkDialogUtils.showDialog(GameActivity.this,"测试");
                }
            }
        }
    }

    private void moveDialog(){
        int left = indexColumn * DeviceManager.widthSize;
        int top = indexRow * DeviceManager.widthSize;
//        dialogTx.layout(left,top,left + dialogTx.getWidth(),dialogTx.getHeight());
//        dialogTx.layout(100,100,500,500);
        System.out.println("测试");
//        dialogTx.layout
//        LinearLayoutCompat.LayoutParams params = (LinearLayoutCompat.LayoutParams) dialogLayout.getLayoutParams();
//        params.height = 300;
//        dialogLayout.requestLayout();

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
            initActor();

            handler.postDelayed(onClickBtn,80);

        }
        private void actorCheckAndMove(){
            result = 0;
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
                if(ImgArrManager.npcImageArr == null || checkWall(ImgArrManager.npcImageArr,ax,ay)){
                    return 0; //通过
                }else{
                    return 2; //遇到npc

                }

            }

            else {
                return 1; //遇到障碍物
            }
        }


        private void checkResult(int result,int ax,int ay){
            if(result == 0) {

            }else {
            }

            switch (result){
                case 0:
                    actor.setX(actor.getX() + ax);
                    actor.setY(actor.getY() + ay);
                    break;
                case 1:
                    break;
                case 2:
                    leftDialogTx.setText("测试");

                    break;


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
                        indexRow = row;
                        indexColumn = column;
                        indexValue = value;

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
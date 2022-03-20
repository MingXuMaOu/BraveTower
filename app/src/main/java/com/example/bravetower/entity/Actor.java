package com.example.bravetower.entity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.example.bravetower.R;
import com.example.bravetower.manager.DeviceManager;
import com.example.bravetower.manager.DrawImgArr;
import com.example.bravetower.manager.MyBitmapManager;

public class Actor implements Runnable{

    private String name = "勇者";
    private int level = 1;
    private int hp = 1000;
    private int attack = 50;
    private int defense = 10;
    private int redKey = 1;
    private int yellowKey = 1;
    private int blueKey = 1;
    private int money = 200;
    private int exp = 100;

    private int x;      //x坐标
    private int y;      //y坐标
    private int currentIndex;
    private int fx;     //朝向
    private Bitmap bitmap;
    private int currentFloor;
    private int maxFloor;
    public static boolean floorFlag = false; //false为向上走，true为向下走

    private static Actor actor;

    private Actor(Context context){
        bitmap = MyBitmapManager.getBitmapActorImg();
        new Thread(this).start();
    }

    public static Actor getActor(Context context){
        if(actor == null){
            actor = new Actor(context);
        }
        return actor;
    }

    public void drawActor(Canvas canvas){
        Rect src = new Rect();
        src.left = currentIndex * MyBitmapManager.getBgWidthSize();
        src.right = (currentIndex + 1) * MyBitmapManager.getBgWidthSize();
        src.top = fx * MyBitmapManager.getBgHeightSize();
        src.bottom = (fx + 1) * MyBitmapManager.getBgHeightSize();
        Rect dst = new Rect();
        dst.left = x;
        dst.right = x + DeviceManager.widthSize;
        dst.top = y;
        dst.bottom = y + DeviceManager.heightSize;
        canvas.drawBitmap(bitmap,src,dst,null);
    }

    @Override
    public void run() {
        while (true){
            try{
                Thread.sleep(300);
                currentIndex++;
                if(currentIndex > 2){
                    currentIndex = 0;
                }
            }catch (Exception e){
                Log.i(Actor.class.toString(),e.getMessage());
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getRedKey() {
        return redKey;
    }

    public void setRedKey(int redKey) {
        this.redKey = redKey;
    }

    public int getYellowKey() {
        return yellowKey;
    }

    public void setYellowKey(int yellowKey) {
        this.yellowKey = yellowKey;
    }

    public int getBlueKey() {
        return blueKey;
    }

    public void setBlueKey(int blueKey) {
        this.blueKey = blueKey;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }

    public static boolean isFloorFlag() {
        return floorFlag;
    }

    public static void setFloorFlag(boolean floorFlag) {
        Actor.floorFlag = floorFlag;
    }

    public static Actor getActor() {
        return actor;
    }

    public static void setActor(Actor actor) {
        Actor.actor = actor;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int getFx() {
        return fx;
    }

    public void setFx(int fx) {
        this.fx = fx;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}

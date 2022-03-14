package com.example.bravetower.manager;

public class ImgArrManager {

    //玩家在本层位置
    public static int acX = 0;
    public static int acY = 0;


    //背景图数组
    public static int[][] bgImageArr = null;
    public static int[][] bg2ImageArr = null;
    public static int[][] zawImgaeArr = null;
    public static int[][] stairImageArr = null;
    public static int[][] goodsImageArr = null;
    public static int[][] npcImageArr = null;

    public static void getZAWInfo(){

        bgImageArr = DrawImgArr.bgImageArr001;
        bg2ImageArr = DrawImgArr.bg2ImageArr001;
        zawImgaeArr = DrawImgArr.zawImageArr001;
        stairImageArr = DrawImgArr.stairImageArr001;
        goodsImageArr = DrawImgArr.goodsImageArr001;
        npcImageArr = DrawImgArr.npcImageArr001;

        acX = DrawImgArr.acx001;
        acY = DrawImgArr.acy001;
    }
}

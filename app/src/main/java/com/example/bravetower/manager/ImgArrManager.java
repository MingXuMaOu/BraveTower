package com.example.bravetower.manager;

public class ImgArrManager {
    //背景图数组
    public static int[][] bgImageArr = null;
    public static int[][] bg2ImageArr = null;
    public static int[][] zawImgaeArr = null;
    public static int[][] stairImageArr = null;
    public static int[][] goodsImageArr = null;

    public static void getZAWInfo(){

        bgImageArr = DrawImgArr.bgImageArr001;
        bg2ImageArr = DrawImgArr.bg2ImageArr001;
        zawImgaeArr = DrawImgArr.zawImageArr001;
        stairImageArr = DrawImgArr.stairImageArr001;
        goodsImageArr = DrawImgArr.goodsImageArr001;
    }
}

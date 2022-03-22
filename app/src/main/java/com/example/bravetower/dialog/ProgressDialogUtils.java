package com.example.bravetower.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Point;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

public class ProgressDialogUtils {
    private static MyProgressDialog mProgressDialog;

    /**
     * 显示ProgressDialog
     *
     * @param context
     * @param message
     */
    public static void showProgressDialog(Context context, CharSequence message) {
//        if (mProgressDialog == null) {
            mProgressDialog = new MyProgressDialog(context);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
            mProgressDialog.setCancelable(true);// 设置是否可以通过点击Back键取消
            mProgressDialog.setCanceledOnTouchOutside(true);// 设置在点击Dialog外是否取消Dialog进度条
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
            Point size = new Point();
            mProgressDialog.getWindow().getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;//获取界面的宽度像素
            int height = size.y;
            WindowManager.LayoutParams params = mProgressDialog.getWindow().getAttributes();//一定要用mProgressDialog得到当前界面的参数对象，否则就不是设置ProgressDialog的界面了
            params.alpha = 0.8f;//设置进度条背景透明度
            params.height = height / 8;//设置进度条的高度
            params.gravity = Gravity.LEFT;//设置ProgressDialog的重心
            params.x = 90;
            params.width = 3 * width / 5;//设置进度条的宽度
            params.dimAmount = 0f;//设置半透明背景的灰度，范围0~1，系统默认值是0.5，1表示背景完全是黑色的,0表示背景不变暗，和原来的灰度一样
            mProgressDialog.getWindow().setAttributes(params);//把参数设置给进度条，注意，一定要先show出来才可以再设置，不然就没效果了，因为只有当界面显示出来后才可以获得它的屏幕尺寸及参数等一些信息

            View v = mProgressDialog.getWindow().getDecorView();
            mProgressDialog.setTextSize(v,24);
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.INVISIBLE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View .SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            int u1 = View.INVISIBLE;
            int flag = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            mProgressDialog.getWindow().getDecorView().setSystemUiVisibility(uiOptions);
//				}
//			});
//        } else {
//            mProgressDialog.show();
//        }
        //setDialogText(v);
        //隐藏状态栏和底部的虚拟键
    }

    /**
     * 关闭ProgressDialog
     */
    public static void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

}

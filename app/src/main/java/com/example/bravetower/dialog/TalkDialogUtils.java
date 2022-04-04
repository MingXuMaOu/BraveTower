package com.example.bravetower.dialog;

import static android.view.Gravity.CLIP_VERTICAL;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import com.example.bravetower.R;
import com.example.bravetower.entity.Actor;
import com.example.bravetower.manager.DeviceManager;


public class TalkDialogUtils {

    private static TalkDialog talkDialog;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void showDialog(Context context, String message,int col,int row){
        talkDialog = new TalkDialog(context);
        talkDialog.getWindow().getDecorView().setForegroundGravity(Gravity.CENTER);
        FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        talkDialog.getWindow().getDecorView().setLayoutParams(p);
        Rect rect = new Rect();
        talkDialog.getWindow().getDecorView().getGlobalVisibleRect(rect);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowManager.LayoutParams lp = talkDialog.getWindow().getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            talkDialog.getWindow().setAttributes(lp);
        }
        talkDialog.show();
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_talk_dialog,null);
        talkDialog.getWindow().setContentView(view);
        FadeInTextView fadeInTextView = view.findViewById(R.id.fade_text);
        fadeInTextView.setTextString(message);

        float textSize = fadeInTextView.getTextSize();


        talkDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg);

        talkDialog.getWindow().setGravity(Gravity.LEFT | Gravity.TOP);

        WindowManager.LayoutParams params = talkDialog.getWindow().getAttributes();
        params.dimAmount = 0f;

        if(message.length() > FadeInTextView.size){
            int line = (message.length() - 1) / FadeInTextView.size;
            String arr = message.substring(0,10);
            params.height = (line + 1) * getFontHeight(textSize) + 140;
            params.width = getFontWidth(arr,textSize) + 40;
        }else {
            params.height = getFontHeight(textSize) + 140;
            params.width = getFontWidth(message,textSize) + 40;
        }
        if(col > DeviceManager.widthNum / 2){
            fadeInTextView.setBackgroundResource(R.drawable.left_dialog_bg);
            params.x = col * DeviceManager.widthSize - params.width;
            params.y = row * DeviceManager.heightSize;

        }else {
            fadeInTextView.setBackgroundResource(R.drawable.right_dialog_bg);
            params.x = col * DeviceManager.widthSize + DeviceManager.widthSize;
            params.y = row * DeviceManager.heightSize;

        }

        talkDialog.getWindow().setAttributes(params);

        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.INVISIBLE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View .SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        talkDialog.getWindow().getDecorView().setSystemUiVisibility(uiOptions);

        fadeInTextView.startFadeInAnimation();

    }

    private static int getFontHeight(float fontSize){
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) (fm.bottom - fm.top + fm.leading);
    }

    private static int getFontWidth(String text,float fontSize){
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        return (int) paint.measureText(text);
    }
}

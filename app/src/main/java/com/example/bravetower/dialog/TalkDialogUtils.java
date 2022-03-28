package com.example.bravetower.dialog;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import com.example.bravetower.R;


public class TalkDialogUtils {

    private static TalkDialog talkDialog;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void showDialog(Context context, CharSequence message){
        talkDialog = new TalkDialog(context);
        talkDialog.setMessage(message);
        talkDialog.getWindow().getDecorView().setForegroundGravity(Gravity.CENTER);
        talkDialog.show();

        talkDialog.getWindow().setContentView(R.layout.fragment_talk_dialog);
        talkDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg);

        WindowManager.LayoutParams params = talkDialog.getWindow().getAttributes();
        params.dimAmount = 0f;
        params.x = -30;
        params.y = 0;
        params.height = 500;
        params.width = 500;
        talkDialog.getWindow().setAttributes(params);
//        View v = talkDialog.getWindow().getDecorView();
//        talkDialog.setTextSize(v,24);

        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.INVISIBLE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View .SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        talkDialog.getWindow().getDecorView().setSystemUiVisibility(uiOptions);
        talkDialog.getWindow().setGravity(Gravity.LEFT|Gravity.TOP);
    }
}

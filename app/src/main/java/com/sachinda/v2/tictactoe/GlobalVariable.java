package com.sachinda.v2.tictactoe;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.widget.Button;

import java.util.Random;

public class GlobalVariable extends Application {
    private int style = 1;

    private Context mContext;
    private R.drawable icon;

    public GlobalVariable (R.drawable icon){
        this.icon = icon;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public Context getContext() {
        return mContext;
    }

    public int randomNumber() {

        Random rn = new Random();
        int randomNum = rn.nextInt((9 - 1) + 1) + 1;

        return randomNum;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public static void changeImage(Button x, Button y, Button z, int icon){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.icon =  mContext.getResources().getDrawable(icon, mContext.getTheme());
        } else {
            return resources.getDrawable(id);
        }
        this.icon = mContext.getResources().getDrawable(icon);
        x.setBackgroundResource(R.drawable.icon);
    }
}

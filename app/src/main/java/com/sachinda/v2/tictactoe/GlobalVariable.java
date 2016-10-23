package com.sachinda.v2.tictactoe;

import android.app.Application;
import android.content.Context;

import java.util.Random;

public class GlobalVariable extends Application {
    private int style = 1;

    private Context mContext;

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
        return rn.nextInt((9 - 1) + 1) + 1;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

}

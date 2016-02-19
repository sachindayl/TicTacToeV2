package com.sachinda.v2.tictactoe;

import android.app.Application;

import java.util.Random;

public class GlobalVariable extends Application {
    private int style = 1;

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
}

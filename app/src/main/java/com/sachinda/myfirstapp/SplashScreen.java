package com.sachinda.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class SplashScreen extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "com.sachinda.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.splash);


        Thread splashTimer = new Thread() {
            public void run() {
                try {
                    int splashTime = 0;
                    while (splashTime < 3000) {


                        if (splashTime < 1000) {
                            setText("Loading.");
                        } else if (splashTime >= 1000 && splashTime < 2000) {
                            setText("Loading..");
                        } else if (splashTime >= 2000) {
                            setText("Loading...");
                        }
                        sleep(100);
                        splashTime = splashTime + 100;

                    }
                    Intent intent = new Intent(SplashScreen.this, StartScreen.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }
        };
        splashTimer.start();
    }

    private void setText(final CharSequence text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TextView) findViewById(R.id.loading_text)).setText(text);
            }
        });
    }


}

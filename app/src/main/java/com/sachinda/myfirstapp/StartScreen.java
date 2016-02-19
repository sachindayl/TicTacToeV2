package com.sachinda.myfirstapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class StartScreen extends Activity {
    public static final String PREF_FILE_NAME = "PrefFile";
    AlertDialog.Builder alert;
    AlertDialog dialog;
    Intent boardType, settings;
    Button startBtn, settingsBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Restore preferences
        SharedPreferences setPref = getSharedPreferences(PREF_FILE_NAME, 0);
        int styleType = setPref.getInt("Style", 1);
        ((GlobalVariable) this.getApplication()).setStyle(styleType);

        int style = ((GlobalVariable) this.getApplication()).getStyle();
        if (style == 1) {
            setContentView(R.layout.activity_start_screen);
        } else if (style == 2) {
            setContentView(R.layout.fairy_tale_start_screen);
        }

        startBtn = (Button) findViewById(R.id.start_button);
        startBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boardType = new Intent(StartScreen.this, BoardType.class);
                StartScreen.this.finish();
                startActivity(boardType);
            }
        });

        settingsBtn = (Button) findViewById(R.id.settings_button);
        settingsBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Log.i("clicks", "You clicked settings button");
                settings = new Intent(StartScreen.this, SettingsActivity.class);
                finish();
                startActivity(settings);


            }
        });

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                StartScreen.this.finish();
                                System.exit(0);
                            }
                        }).setNegativeButton("No", null).show();
    }


}

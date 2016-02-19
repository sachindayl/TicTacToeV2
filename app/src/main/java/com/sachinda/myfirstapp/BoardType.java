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

public class BoardType extends Activity {
    public static final String PREF_FILE_NAME = "PrefFile";
    AlertDialog.Builder alert;
    AlertDialog dialog;
    Intent threeX3OneP, threeX3TwoP, fourX4TwoP, startPage;
    Button threeIntoThreeBtn, fourIntoFourBtn;

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
            setContentView(R.layout.activity_board_type);
        } else if (style == 2) {
            setContentView(R.layout.activity_board_type_fairy);
        }

        threeIntoThreeBtn = (Button) findViewById(R.id.threex3riptide_button);
        threeIntoThreeBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("clicks", "You clicked start button");

                alert = new AlertDialog.Builder(BoardType.this);
                alert.setMessage("One Player or Two Player?");

                alert.setPositiveButton("Two Player",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                threeX3TwoP = new Intent(BoardType.this,
                                        TwoPlayerActivity.class);
                                finish();
                                startActivity(threeX3TwoP);

                            }
                        });
                alert.setNeutralButton("One Player",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                threeX3OneP = new Intent(BoardType.this,
                                        OnePlayerActivity.class);
                                finish();
                                startActivity(threeX3OneP);

                            }
                        });
                dialog = alert.create();

                dialog.show();
            }
        });

        fourIntoFourBtn = (Button) findViewById(R.id.fourx4riptide_button);
        fourIntoFourBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Log.i("clicks", "You clicked settings button");
                fourX4TwoP = new Intent(BoardType.this, FourInToFourTwoPlayer.class);
                finish();
                startActivity(fourX4TwoP);


            }
        });

    }

    public void onBackPressed() {
        startPage = new Intent(BoardType.this, StartScreen.class);
        BoardType.this.finish();
        startActivity(startPage);
    }


}
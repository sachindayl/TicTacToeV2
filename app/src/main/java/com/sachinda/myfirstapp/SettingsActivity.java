package com.sachinda.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;

public class SettingsActivity extends Activity {

    public static final String PREF_FILE_NAME = "PrefFile";
    Intent back, settings;

    RadioButton radio1, radio2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set content view AFTER ABOVE sequence (to avoid crash)
        Log.i("arrival", "You came to settings page");

        radio1 = (RadioButton) findViewById(R.id.radio_redblk);
        radio2 = (RadioButton) findViewById(R.id.radio_bluesilvr);


        int style = ((GlobalVariable) this.getApplication()).getStyle();
        if (style == 1) {
            setContentView(R.layout.activity_settings);

        } else if (style == 2) {
            setContentView(R.layout.blue_silver_settings_activity);

        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?

        boolean checked = ((RadioButton) view).isChecked();
        settings = getIntent();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_redblk:
                if (checked)
                    ((GlobalVariable) this.getApplication()).setStyle(1);
                finish();
                startActivity(settings);
                break;
            case R.id.radio_bluesilvr:
                if (checked)
                    ((GlobalVariable) this.getApplication()).setStyle(2);
                finish();
                startActivity(settings);
                break;
        }
    }

    public void onBackPressed() {
        back = new Intent(SettingsActivity.this, StartScreen.class);
        SettingsActivity.this.finish();
        startActivity(back);
    }

    @Override
    protected void onStop() {
        super.onStop();

        int style = ((GlobalVariable) this.getApplication()).getStyle();
        SharedPreferences settings = getSharedPreferences(PREF_FILE_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("Style", style);

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context

        // Commit the edits!
        editor.commit();
    }
}

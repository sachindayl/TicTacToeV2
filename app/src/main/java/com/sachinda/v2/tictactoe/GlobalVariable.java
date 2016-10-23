package com.sachinda.v2.tictactoe;

import android.app.Application;
import android.content.Context;

import java.util.Random;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

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

    /**
     * This is a subclass of {@link Application} used to provide shared objects for this app, such as
     * the {@link Tracker}.
     */
        private Tracker mTracker;

        /**
         * Gets the default {@link Tracker} for this {@link Application}.
         * @return tracker
         */
        synchronized public Tracker getDefaultTracker() {
            if (mTracker == null) {
                GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
                // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
                mTracker = analytics.newTracker(R.xml.global_tracker);
            }
            return mTracker;
        }

}

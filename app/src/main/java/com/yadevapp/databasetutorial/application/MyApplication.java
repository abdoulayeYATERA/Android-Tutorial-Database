package com.yadevapp.databasetutorial.application;

import android.app.Application;
import android.util.Log;

/**
 * Created by salou on 7/15/16.
 */
public class MyApplication extends Application {
    private final String TAG = getClass().getSimpleName();

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
    }
}

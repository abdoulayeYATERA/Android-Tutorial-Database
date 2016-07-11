package com.yadevapp.databasetutorial.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by abdoulaye on 7/11/16.
 * the database helper class
 * create the database
 * update the database structure if need
 */
public class MovieDatabase extends SQLiteOpenHelper {
    private final String TAG = getClass().getSimpleName();

    public MovieDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d(TAG, "MovieDatabase");
    }

    /**
     * create the database
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG,"onCreate");
    }

    /**
     * update the databse structure
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "onUpgrade");
    }
}

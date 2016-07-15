package com.yadevapp.databasetutorial.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.yadevapp.databasetutorial.R;

import java.io.File;

/**
 * Created by abdoulaye on 7/11/16.
 * the database helper class
 * create the database
 * update the database structure if need
 */
public class MovieDatabase extends SQLiteOpenHelper {
    private final String TAG = getClass().getSimpleName();

    //database name
    public static final String NAME = "movieDatabase";
    //database authority
    public static final String AUTHORITY = "com.yadevapp.databasetutorial.database.MovieDatabaseContentProvider";
    //database version
    public static final int  VERSION = 1;
    //movieTable name
    public static final String MOVIE_TABLE_NAME = "movieTable";
    //movieTable uri
    public static final Uri MOVIE_TABLE_URI = Uri.parse("content://" + AUTHORITY + File.separator + MOVIE_TABLE_NAME);
    //movieTable uri match int
    public static final int MOVIE_TABLE_URI_MATCH_INT = 689;
    //movieTable columns
    public static final String KEY_NAME = "name";
    public static final String KEY_YEAR = "year";
    public static final String KEY_ID = "_id";
    public static final String KEY_TYPE = "type";

    public MovieDatabase(Context context) {
        super(context, NAME, null, VERSION);
        Log.d(TAG, "MovieDatabase");
    }

    /**
     * create the database
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate");

        String createMovieTableString =
                " CREATE TABLE " + MOVIE_TABLE_NAME + " ("
                        + KEY_ID + " INTEGER PRIMARY KEY ,"
                        + KEY_NAME  + " TEXT ,"
                        + KEY_YEAR + " INTEGER ,"
                        + KEY_TYPE + " TEXT)";
        sqLiteDatabase.execSQL(createMovieTableString);
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
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MOVIE_TABLE_NAME);
        // Create tables again
        onCreate(sqLiteDatabase);
        Log.d(TAG," database update");
    }
}

package com.yadevapp.databasetutorial.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import java.io.File;

/**
 * Created by salou on 7/11/16.
 * the movie database content provider
 */
public class MovieDatabaseContentProvider extends ContentProvider {
    private  final String TAG = getClass().getSimpleName();
    private MovieDatabase mMovieDatabase;
    /**
     * the uri matcher is used to know to wich table the request is steered,
     * in this case it is not really revelant because we have only one table in
     * the database
     */
    private static UriMatcher mURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        mURIMatcher.addURI(MovieDatabase.AUTHORITY,
                MovieDatabase.MOVIE_TABLE_NAME,
                MovieDatabase.MOVIE_TABLE_URI_MATCH_INT);
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate");
        mMovieDatabase = new MovieDatabase(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "insert " + uri.toString());
        Cursor cursor = null;

        if (mURIMatcher.match(uri) == MovieDatabase.MOVIE_TABLE_URI_MATCH_INT) {
            SQLiteDatabase dp = mMovieDatabase.getWritableDatabase();
            cursor = dp.query(MovieDatabase.MOVIE_TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder);
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        } else {
            Log.e(TAG, "wrong uri");
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        Log.d(TAG, "insert");

        if(mURIMatcher.match(uri) == MovieDatabase.MOVIE_TABLE_URI_MATCH_INT) {
            SQLiteDatabase db = mMovieDatabase.getWritableDatabase();

            if(db.insert(MovieDatabase.MOVIE_TABLE_NAME, null, contentValues) != -1) {
                Log.d(TAG, "insert suceed");
            } else {
                Log.e(TAG, "insert failed");
            }

            getContext().getContentResolver().notifyChange(uri, null);
        } else {
            Log.e(TAG, "wrong uri");
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}

package com.yadevapp.databasetutorial.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.yadevapp.databasetutorial.R;
import com.yadevapp.databasetutorial.adapter.MovieListCursorAdapter;
import com.yadevapp.databasetutorial.database.MovieDatabase;
import com.yadevapp.databasetutorial.database.MovieDatabaseContentProvider;
import com.yadevapp.databasetutorial.element.Movie;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();
    private ListView mListView;
    private Button mUpdateListButton;
    private Button mAddMovieButton;
    private EditText mNewMovieNameEditText;
    private EditText mNewMovieYearEditText;
    private EditText mNewMovieTypeEditText;
    private MovieListCursorAdapter mMovieListCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get the views
        mListView = (ListView) findViewById(R.id.activity_main_movie_listview);
        mNewMovieNameEditText = (EditText) findViewById(R.id.activity_main_movie_name_edittext);
        mNewMovieYearEditText = (EditText) findViewById(R.id.activity_main_movie_year_edittext);
        mNewMovieTypeEditText = (EditText) findViewById(R.id.activity_main_movie_type_edittext);
        mUpdateListButton = (Button) findViewById(R.id.activity_main_update_button);
        mAddMovieButton = (Button) findViewById(R.id.activity_main_add_movie_button);
        //instanciate the adapter
        mMovieListCursorAdapter = new MovieListCursorAdapter(this, null, 0);
        //set the adapter to the listview
        mListView.setAdapter(mMovieListCursorAdapter);
        //set the buttons listeners
        mUpdateListButton.setOnClickListener(this);
        mAddMovieButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick");
        int viewId = view.getId();

        switch (viewId) {
            case R.id.activity_main_add_movie_button:
                //create Movie instance from edit texts
                Movie movieToAdd = new Movie();
                String movieToAddName = mNewMovieNameEditText.getText().toString();
                String movieToAddType = mNewMovieTypeEditText.getText().toString();
                int movieToAddYear = Integer.valueOf(mNewMovieYearEditText.getText().toString());

                if (movieToAddName != null && !movieToAddName.isEmpty()
                        && movieToAddType != null && !movieToAddType.isEmpty()
                        && movieToAddYear > 0) {
                    //the form is correctly completed
                    movieToAdd.setmName(movieToAddName);
                    movieToAdd.setmType(movieToAddType);
                    movieToAdd.setmYear(movieToAddYear);
                    //add the movie to the table
                    addMovieToDatabase(movieToAdd);
                } else {
                    //the form is not correctly completed
                    Toast.makeText(MainActivity.this,
                            getResources().getString(R.string.wrong_form_toast_message),
                            Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.activity_main_update_button:
                updateMovieList();
                break;
        }
    }

    /**
     * do the query on the table to get the cursor
     * and update the adapter with the cursor
     */
    public void updateMovieList() {
        Log.d(TAG, "updateMovieList");
       Cursor cursor = getContentResolver().query(
               MovieDatabase.MOVIE_TABLE_URI,
               null,
               null,
               null,
               null
       );
        mMovieListCursorAdapter.swapCursor(cursor);
    }

    /**
     * @param movieToAdd
     */
    public void addMovieToDatabase(Movie movieToAdd) {
        Log.d(TAG, "addMovieToDatabase");
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieDatabase.KEY_NAME, movieToAdd.getmName());
        contentValues.put(MovieDatabase.KEY_TYPE, movieToAdd.getmType());
        contentValues.put(MovieDatabase.KEY_YEAR, movieToAdd.getmYear());

        getContentResolver().insert(
                MovieDatabase.MOVIE_TABLE_URI,
                contentValues
        );
        Toast.makeText(MainActivity.this, getString(R.string.database_insert_toast_message), Toast.LENGTH_LONG).show();
    }
}

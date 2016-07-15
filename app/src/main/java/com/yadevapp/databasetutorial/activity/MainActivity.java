package com.yadevapp.databasetutorial.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.yadevapp.databasetutorial.R;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private ListView mListView;
    private Button mUpdateListButton;
    private EditText mNewMovieNameEditText;
    private EditText mNewMovieYearEditText;
    private EditText mNewMovieTypeEditText;

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
    }
}

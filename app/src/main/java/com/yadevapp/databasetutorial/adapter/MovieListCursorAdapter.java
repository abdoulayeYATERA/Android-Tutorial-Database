package com.yadevapp.databasetutorial.adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.yadevapp.databasetutorial.R;
import com.yadevapp.databasetutorial.database.MovieDatabase;
import com.yadevapp.databasetutorial.element.Movie;


import java.util.ArrayList;

/**
 * Created by abdoulaye on 7/7/16.
 */
public class MovieListCursorAdapter extends CursorAdapter  {
    private final String TAG = getClass().getSimpleName();
    private Cursor mMovieCursor;
    private Context mContext;


    public MovieListCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
        mContext = context;
        mMovieCursor = cursor;
    }

    /**
     * @param i position in the cursor
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d(TAG, "getView position = " + i);
        MovieAdapterViewHolder movieAdapterViewHolder;

        if (view == null) {
            //if view null inflate the view
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.item_listview, viewGroup, false);
            //instanciate the view holder
            movieAdapterViewHolder = new MovieAdapterViewHolder(view);
            //set the view holder has view's tag
            view.setTag(movieAdapterViewHolder);
        } else {
            //the view is not null, we reuse (no need to inflate)
            //we get the view holder from the view's tag
            movieAdapterViewHolder = (MovieAdapterViewHolder) view.getTag();
        }
        //move the cursor to the good position
        mMovieCursor.move(i);
        //get the movie of that position
        Movie movie = new Movie();
        movie.setmName(mMovieCursor.getString(mMovieCursor.getColumnIndex(MovieDatabase.KEY_NAME)));
        movie.setmType(mMovieCursor.getString(mMovieCursor.getColumnIndex(MovieDatabase.KEY_TYPE)));
        movie.setmYear(mMovieCursor.getInt(mMovieCursor.getColumnIndex(MovieDatabase.KEY_YEAR)));
        //update the textviews
        //the textviews retrieved from the viewHolder
        movieAdapterViewHolder.getTitleTextView().setText(movie.getmName());
        movieAdapterViewHolder.getYearTextView().setText(String.valueOf(movie.getmYear()));//we must convert the  year (int) to string
        movieAdapterViewHolder.getTypetextView().setText(movie.getmType());
        //return the view
        return view;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }


    /**
     * here is the viewholder class
     * the goal of this class is to prevent
     * the findViewById call in the getView (findviewbyid takes time because of parsing)
     */
    public class MovieAdapterViewHolder {
        private View listViewRow;
        private TextView titleTextView;
        private TextView yearTextView;
        private TextView typetextView;

        public MovieAdapterViewHolder (View row) {
            listViewRow = row;
            titleTextView = (TextView) row.findViewById(R.id.item_listview_title);
            yearTextView = (TextView) row.findViewById(R.id.item_listview_year);
            typetextView = (TextView) row.findViewById(R.id.item_listview_type);
        }

        //getters
        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TextView getYearTextView() {
            return yearTextView;
        }

        public TextView getTypetextView() {
            return typetextView;
        }
    }
}

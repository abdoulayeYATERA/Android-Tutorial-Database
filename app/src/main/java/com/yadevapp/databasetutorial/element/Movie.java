package com.yadevapp.databasetutorial.element;

/**
 * Created by abdoulaye  on 7/7/16.
 * class representing a movie
 */
public class Movie {
    private final String TAG = getClass().getSimpleName();

    private String mName;
    private int mYear;
    private String mType;
    private int mDuration;
    private int mStarsNumber;

    //getters

    public String getmName() {
        return mName;
    }

    public int getmYear() {
        return mYear;
    }

    public String getmType() {
        return mType;
    }

    public int getmDuration() {
        return mDuration;
    }

    public int getmStarsNumber() {
        return mStarsNumber;
    }

    //setters

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmYear(int mYear) {
        this.mYear = mYear;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public void setmDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    public void setmStarsNumber(int mStarsNumber) {
        this.mStarsNumber = mStarsNumber;
    }
}

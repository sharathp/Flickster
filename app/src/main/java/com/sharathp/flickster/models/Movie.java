package com.sharathp.flickster.models;

public class Movie {
    private long mId;
    private String mTitle;
    private String mPosterPath;
    private String mBackdropPath;
    private float mVoteAverage;
    private boolean mIsVideo;

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public float getVoteAverage() {
        return mVoteAverage;
    }

    public boolean isVideo() {
        return mIsVideo;
    }
}

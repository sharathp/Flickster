package com.sharathp.flickster.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("id")
    private long mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("poster_path")
    private String mPosterPath;

    @SerializedName("backdrop_path")
    private String mBackdropPath;

    @SerializedName("vote_average")
    private float mVoteAverage;

    @SerializedName("video")
    private boolean mVideo;

    public long getId() {
        return mId;
    }

    public void setId(final long id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(final String title) {
        mTitle = title;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(final String overview) {
        mOverview = overview;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(final String posterPath) {
        mPosterPath = posterPath;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(final String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public float getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(final float voteAverage) {
        mVoteAverage = voteAverage;
    }

    public boolean isVideo() {
        return mVideo;
    }

    public void setVideo(final boolean video) {
        mVideo = video;
    }
}

package com.sharathp.flickster.models;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Movie implements Serializable {
    private static final String TAG = Movie.class.getSimpleName();

    private static final long serialVersionUID = 1L;
    private static final String DATE_FORMAT_RELEASE_DATE = "yyyy-MM-dd";

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

    @SerializedName("popularity")
    private float mPopularity;

    @SerializedName("release_date")
    private String mReleaseDate;

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

    public float getPopularity() {
        return mPopularity;
    }

    public void setPopularity(final float popularity) {
        mPopularity = popularity;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(final String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public static Date parseReleaseDate(final String releaseDate) {
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_RELEASE_DATE);
        try {
            return sdf.parse(releaseDate);
        } catch (final ParseException e) {
            Log.e(TAG, "Error parsing release date: " + releaseDate, e);
            return null;
        }
    }
}

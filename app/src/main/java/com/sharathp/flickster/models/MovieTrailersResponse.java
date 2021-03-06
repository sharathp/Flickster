package com.sharathp.flickster.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieTrailersResponse {

    @SerializedName("id")
    private long mId;

    @SerializedName("youtube")
    private List<Video> mResults;

    public long getId() {
        return mId;
    }

    public void setId(final long id) {
        mId = id;
    }

    public List<Video> getResults() {
        return mResults;
    }

    public void setResults(final List<Video> results) {
        mResults = results;
    }
}

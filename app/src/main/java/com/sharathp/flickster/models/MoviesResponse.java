package com.sharathp.flickster.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {

    @SerializedName("page")
    private int mPage;

    @SerializedName("results")
    private List<Movie> mMovies;

    @SerializedName("total_pages")
    private int mTotalPages;

    @SerializedName("total_results")
    private int mTotalResults;

    public int getPage() {
        return mPage;
    }

    public void setPage(final int page) {
        mPage = page;
    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(final List<Movie> movies) {
        mMovies = movies;
    }

    public int getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(final int totalPages) {
        mTotalPages = totalPages;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(final int totalResults) {
        mTotalResults = totalResults;
    }
}
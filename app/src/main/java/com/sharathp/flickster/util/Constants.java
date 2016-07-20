package com.sharathp.flickster.util;

public class Constants {
    public static final String API_QUERY_PARAM = "api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String URL_BASE = " https://api.themoviedb.org/3";
    public static final String URL_LATEST_MOVIES = URL_BASE + "/movie/now_playing?" + API_QUERY_PARAM;

    public static final String getImageUrl(final String relativePath) {
        return null;
    }

    public static final String getLatestMoviesUrl() {
        return URL_LATEST_MOVIES;
    }
}

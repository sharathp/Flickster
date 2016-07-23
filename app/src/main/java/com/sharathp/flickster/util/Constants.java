package com.sharathp.flickster.util;

public class Constants {
    private static final String API_QUERY_PARAM = "api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private static final String URL_BASE = " https://api.themoviedb.org/3";
    private static final String URL_LATEST_MOVIES = URL_BASE + "/movie/now_playing?" + API_QUERY_PARAM;

    private static final String URL_BASE_MOVIE_IMAGE = "https://image.tmdb.org/t/p";
    private static final String URL_MOVIE_POSTER = URL_BASE_MOVIE_IMAGE + "/w342/%s";
    private static final String URL_BACKDROP_IMAGE = URL_BASE_MOVIE_IMAGE + "/w780/%s";

    public static final int ROUND_TRANSFORMATION_RADIUS = 10;
    public static final int ROUND_TRANSFORMATION_MARGIN = 5;

    public static final String getPosterImageUrl(final String relativePath) {
        return String.format(URL_MOVIE_POSTER, relativePath);
    }

    public static final String getBackdropImageUrl(final String relativePath) {
        return String.format(URL_BACKDROP_IMAGE, relativePath);
    }

    public static final String getLatestMoviesUrl() {
        return URL_LATEST_MOVIES;
    }
}

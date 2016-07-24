package com.sharathp.flickster.repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.sharathp.flickster.models.Movie;
import com.sharathp.flickster.models.MovieTrailersResponse;
import com.sharathp.flickster.models.MoviesResponse;
import com.sharathp.flickster.models.Video;
import com.sharathp.flickster.util.Constants;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Repository to retrieve
 */
public class MovieRepository {
    private final Gson mGson;

    public MovieRepository() {
        mGson = new GsonBuilder().create();
    }

    public void retrieveAllMovies(final MoviesListCallback moviesListCallback) {
        final AsyncHttpClient client = new AsyncHttpClient();
        client.get(Constants.getLatestMoviesUrl(), new TextHttpResponseHandler() {
            @Override
            public void onSuccess(final int statusCode, final Header[] headers, final String res) {
                final MoviesResponse moviesResponse = mGson.fromJson(res, MoviesResponse.class);
                moviesListCallback.moviesRetrievedSuccessfully(moviesResponse.getMovies());
            }

            @Override
            public void onFailure(final int statusCode, final Header[] headers, final String res, final Throwable t) {
                moviesListCallback.moviesRetrievalFailed();
            }
        });
    }

    public void retrieveMovieVideos(final long movieId, final MovieVideosCallback movieVideosCallback) {
        final AsyncHttpClient client = new AsyncHttpClient();
        client.get(Constants.getMovieTrailersUrl(movieId), new TextHttpResponseHandler() {
            @Override
            public void onSuccess(final int statusCode, final Header[] headers, final String res) {
                final MovieTrailersResponse movieTrailersResponse = mGson.fromJson(res, MovieTrailersResponse.class);
                movieVideosCallback.videosRetrievedSuccessfully(movieTrailersResponse.getResults());
            }

            @Override
            public void onFailure(final int statusCode, final Header[] headers, final String res, final Throwable t) {
                movieVideosCallback.videosRetrievalFailed();
            }
        });
    }

    public interface MoviesListCallback {

        /**
         * Notify that movies were retrieved.
         *
         * @param movies - movies
         */
        void moviesRetrievedSuccessfully(List<Movie> movies);

        /**
         * Notify movies retrieval failed.
         */
        void moviesRetrievalFailed();
    }

    public interface MovieVideosCallback {

        /**
         * Notify that videos were retrieved.
         *
         * @param videos - videos
         */
        void videosRetrievedSuccessfully(List<Video> videos);

        /**
         * Notify movies retrieval failed.
         */
        void videosRetrievalFailed();
    }
}

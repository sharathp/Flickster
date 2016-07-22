package com.sharathp.flickster.repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.sharathp.flickster.models.Movie;
import com.sharathp.flickster.models.MoviesResponse;
import com.sharathp.flickster.util.Constants;

import java.lang.ref.WeakReference;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Repository to retrieve
 */
public class MovieRepository {
    private WeakReference<Callback> mCallback;
    private final Gson mGson;

    public MovieRepository(final Callback callback) {
        mCallback = new WeakReference<>(callback);
        mGson = new GsonBuilder().create();
    }

    public void retrieveAllMovies() {
        final AsyncHttpClient client = new AsyncHttpClient();
        client.get(Constants.getLatestMoviesUrl(), new TextHttpResponseHandler() {
            @Override
            public void onSuccess(final int statusCode, final Header[] headers, final String res) {
                final MoviesResponse moviesResponse = mGson.fromJson(res, MoviesResponse.class);

                final Callback callback = getCallback();
                if (callback != null) {
                    callback.moviesRetrievedSuccessfully(moviesResponse.getMovies());
                }
            }

            @Override
            public void onFailure(final int statusCode, final Header[] headers, final String res, final Throwable t) {
                final Callback callback = getCallback();
                if (callback == null) {
                    return;
                }

                callback.moviesRetrievedFailed();
            }

            private Callback getCallback() {
                return mCallback.get();
            }
        });
    }

    public interface Callback {

        /**
         * Notify that movies were retrieved.
         *
         * @param movies - movies
         */
        void moviesRetrievedSuccessfully(List<Movie> movies);

        /**
         * Notify movies retrieval failed.
         */
        void moviesRetrievedFailed();
    }
}

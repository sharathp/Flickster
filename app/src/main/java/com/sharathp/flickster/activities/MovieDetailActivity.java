package com.sharathp.flickster.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sharathp.flickster.R;
import com.sharathp.flickster.models.Movie;

import butterknife.ButterKnife;

public class MovieDetailActivity extends BaseYoutubeActivity {
    public static final String EXTRA_MOVIE = MovieDetailActivity.class.getSimpleName() + ":MOVIE";

    private Movie mMovie;

    public static Intent createIntent(final Context context, final Movie movie) {
        final Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        populateIntent(intent, movie.getId());
        return intent;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(this);

        mMovie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
    }
}

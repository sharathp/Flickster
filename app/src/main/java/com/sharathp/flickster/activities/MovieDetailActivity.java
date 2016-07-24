package com.sharathp.flickster.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.sharathp.flickster.R;
import com.sharathp.flickster.databinding.ActivityMovieDetailBinding;
import com.sharathp.flickster.models.Movie;

public class MovieDetailActivity extends BaseYoutubeActivity {
    public static final String EXTRA_MOVIE = MovieDetailActivity.class.getSimpleName() + ":MOVIE";
    private Movie mMovie;

    private ActivityMovieDetailBinding mBinding;

    public static Intent createIntent(final Context context, final Movie movie) {
        final Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        populateIntent(intent, movie.getId());
        return intent;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        mMovie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

        // the super-class relies on ButterKnife to bind the mYouTubePlayerView, but here
        // we use data binding to assign the mYouTubePlayerView
        mYouTubePlayerView = mBinding.ypvPlay;

        mBinding.setMovie(mMovie);
    }
}

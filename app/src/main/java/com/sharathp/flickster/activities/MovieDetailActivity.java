package com.sharathp.flickster.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sharathp.flickster.R;
import com.sharathp.flickster.models.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends BaseYoutubeActivity {
    public static final String EXTRA_MOVIE = MovieDetailActivity.class.getSimpleName() + ":MOVIE";

    private Movie mMovie;

    @BindView(R.id.tv_movie_title)
    TextView mTitleTextView;

    @BindView(R.id.tv_movie_desc)
    TextView mDescTextView;

    @BindView(R.id.rb_votes)
    RatingBar mVotesRatingBar;

    @BindView(R.id.tv_movie_popularity)
    TextView mMoviePopularityTextView;

    @BindView(R.id.tv_movie_release_date)
    TextView mMovieReleaseDateTextView;

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
        mTitleTextView.setText(mMovie.getTitle());
        mDescTextView.setText(mMovie.getOverview());
        mVotesRatingBar.setRating(mMovie.getVoteAverage());
        mMoviePopularityTextView.setText(Float.toString(mMovie.getPopularity()));
        mMovieReleaseDateTextView.setText(mMovie.getReleaseDate());
    }
}

package com.sharathp.flickster.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sharathp.flickster.R;
import com.sharathp.flickster.models.Movie;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends BaseYoutubeActivity {
    public static final String EXTRA_MOVIE = MovieDetailActivity.class.getSimpleName() + ":MOVIE";
    private static final String DATE_FORMAT_RELEASE_DATE = "MMMM d, yyyy";
    private static final String FORMAT_POPULARITY = "#.00";

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
        mMoviePopularityTextView.setText(getFormattedPopularity(mMovie.getPopularity()));
        mMovieReleaseDateTextView.setText(getFormattedDate(mMovie.parseReleaseDate()));
        mVotesRatingBar.setRating(mMovie.getVoteAverage());
    }

    private String getFormattedDate(final Date releaseDate) {
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_RELEASE_DATE);
        return sdf.format(releaseDate);
    }

    private String getFormattedPopularity(final float popularity) {
        final DecimalFormat decimalFormat = new DecimalFormat(FORMAT_POPULARITY);
        return decimalFormat.format(popularity);
    }
}

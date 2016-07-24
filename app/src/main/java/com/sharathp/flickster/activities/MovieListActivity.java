package com.sharathp.flickster.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.sharathp.flickster.FlicksterApplication;
import com.sharathp.flickster.R;
import com.sharathp.flickster.databinding.ActivityMovieListBinding;
import com.sharathp.flickster.models.Movie;
import com.sharathp.flickster.repositories.MovieRepository;
import com.sharathp.flickster.views.MovieListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends AppCompatActivity implements MovieRepository.MoviesListCallback, MovieListAdapter.MovieItemCallback {
    private MovieListAdapter mMovieListAdapter;
    private MovieRepository mMovieRepository;

    private ActivityMovieListBinding mBinding;
    private SwipeRefreshLayout mMoviesSwipeContainer;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list);
        mMovieListAdapter = new MovieListAdapter(new ArrayList<Movie>(), this);
        mMovieRepository = FlicksterApplication.from(this).getComponent().getMovieRepository();

        setSupportActionBar(mBinding.toolbarLayout.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setToolbarTitleFont();

        final RecyclerView moviesRecyclerView = mBinding.rvMovies;
        moviesRecyclerView.setAdapter(mMovieListAdapter);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMoviesSwipeContainer = mBinding.srlMovies;
        mMoviesSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // show spinner..
                mMovieListAdapter.setMovies(null);
                mMovieRepository.retrieveAllMovies(MovieListActivity.this);
            }
        });

        mMovieRepository.retrieveAllMovies(this);
    }

    @Override
    public void moviesRetrievedSuccessfully(final List<Movie> movies) {
        mMovieListAdapter.addMovies(movies);
        mMoviesSwipeContainer.setRefreshing(false);
    }

    @Override
    public void moviesRetrievalFailed() {
        mMoviesSwipeContainer.setRefreshing(false);
        Toast.makeText(this, "Unable to retrieve Movies", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMovieSelected(final Movie movie) {
        final Intent intent = MovieDetailActivity.createIntent(this, movie);
        startActivity(intent);
    }

    @Override
    public void onPopularMovieSelected(final Movie movie) {
        final Intent intent = YoutubeActivity.createIntent(this, movie.getId());
        startActivity(intent);
    }

    private void setToolbarTitleFont() {
        final TextView toolbarTextView = mBinding.toolbarLayout.toolbarTitle;
        final Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        // Assign the typeface to the view
        toolbarTextView.setTypeface(font);
    }
}

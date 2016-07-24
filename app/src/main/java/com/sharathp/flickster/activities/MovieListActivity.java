package com.sharathp.flickster.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sharathp.flickster.FlicksterApplication;
import com.sharathp.flickster.R;
import com.sharathp.flickster.models.Movie;
import com.sharathp.flickster.repositories.MovieRepository;
import com.sharathp.flickster.views.MovieListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity extends AppCompatActivity implements MovieRepository.MoviesListCallback, MovieListAdapter.MovieItemCallback {
    private MovieListAdapter mMovieListAdapter;
    private MovieRepository mMovieRepository;

    @BindView(R.id.rv_movies)
    RecyclerView mMoviesRecyclerView;

    @BindView(R.id.srl_movies)
    SwipeRefreshLayout mMoviesSwipeContainer;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        ButterKnife.bind(this);

        mMovieListAdapter = new MovieListAdapter(new ArrayList<Movie>(), this);
        mMoviesRecyclerView.setAdapter(mMovieListAdapter);
        mMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMovieRepository = FlicksterApplication.from(this).getComponent().getMovieRepository();
        mMovieRepository.retrieveAllMovies(this);

        mMoviesSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // show spinner..
                mMovieListAdapter.setMovies(null);
                mMovieRepository.retrieveAllMovies(MovieListActivity.this);
            }
        });
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
}

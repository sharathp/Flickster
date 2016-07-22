package com.sharathp.flickster.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sharathp.flickster.R;
import com.sharathp.flickster.models.Movie;
import com.sharathp.flickster.repositories.MovieRepository;
import com.sharathp.flickster.views.MovieListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity extends AppCompatActivity implements MovieRepository.Callback {
    private MovieListAdapter mMovieListAdapter;
    private MovieRepository mMovieRepository;

    @BindView(R.id.rv_movies)
    RecyclerView mMoviesRecyclerView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        ButterKnife.bind(this);

        mMovieListAdapter = new MovieListAdapter(new ArrayList<Movie>(), this);
        mMoviesRecyclerView.setAdapter(mMovieListAdapter);
        mMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMovieRepository = new MovieRepository(this);
        mMovieRepository.retrieveAllMovies();
    }

    @Override
    public void moviesRetrievedSuccessfully(final List<Movie> movies) {
        mMovieListAdapter.addMovies(movies);
    }

    @Override
    public void moviesRetrievedFailed() {
        Toast.makeText(this, "Unable to retrieve Movies", Toast.LENGTH_LONG).show();
    }
}

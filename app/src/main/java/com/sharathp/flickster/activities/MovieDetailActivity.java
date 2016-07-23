package com.sharathp.flickster.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.sharathp.flickster.BuildConfig;
import com.sharathp.flickster.R;
import com.sharathp.flickster.models.Movie;
import com.sharathp.flickster.models.Video;
import com.sharathp.flickster.repositories.MovieRepository;
import com.sharathp.flickster.util.MovieUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends YouTubeBaseActivity implements MovieRepository.MovieVideosCallback {
    public static final String EXTRA_MOVIE = MovieDetailActivity.class.getSimpleName() + ":MOVIE";

    private MovieRepository mMovieRepository;
    private Movie mMovie;

    @BindView(R.id.ypv_play)
    YouTubePlayerView mYouTubePlayerView;

    public static Intent createIntent(final Context context, final Movie movie) {
        final Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(this);

        mMovie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        mMovieRepository = new MovieRepository();
        mMovieRepository.retrieveMovieVideos(mMovie.getId(), this);
    }

    @Override
    public void videosRetrievedSuccessfully(final List<Video> videos) {
        if (videos == null || videos.isEmpty()) {
            Toast.makeText(MovieDetailActivity.this, "No Videos to play", Toast.LENGTH_LONG).show();
            return;
        }
        final String videoKey = MovieUtil.getVideoKeyToPlay(videos);
        playVideo(videoKey);
    }

    @Override
    public void videosRetrievalFailed() {
        Toast.makeText(MovieDetailActivity.this, "Video Retrieval Failed!", Toast.LENGTH_LONG).show();
    }

    private void playVideo(final String videoKey) {
        mYouTubePlayerView.initialize(BuildConfig.YOUTUBE_API_TOKEN,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(final YouTubePlayer.Provider provider,
                                                        final YouTubePlayer youTubePlayer, final boolean b) {
                        youTubePlayer.cueVideo(videoKey);
                    }

                    @Override
                    public void onInitializationFailure(final YouTubePlayer.Provider provider,
                                                        final YouTubeInitializationResult youTubeInitializationResult) {
                        Toast.makeText(MovieDetailActivity.this, "Youtube Failed!", Toast.LENGTH_LONG).show();
                    }
                });
    }
}

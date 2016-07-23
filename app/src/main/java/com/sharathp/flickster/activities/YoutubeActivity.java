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
import com.sharathp.flickster.models.Video;
import com.sharathp.flickster.repositories.MovieRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YoutubeActivity extends YouTubeBaseActivity implements MovieRepository.MovieVideosCallback {
    public static final String EXTRA_MOVIE_ID = YoutubeActivity.class.getSimpleName() + ":MOVIE_ID";

    private MovieRepository mMovieRepository;

    @BindView(R.id.ypv_play)
    YouTubePlayerView mYouTubePlayerView;

    public static Intent createIntent(final Context context, final long movieId) {
        final Intent intent = new Intent(context, YoutubeActivity.class);
        intent.putExtra(EXTRA_MOVIE_ID, movieId);
        return intent;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        ButterKnife.bind(this);
        mMovieRepository = new MovieRepository();
        final long movieId = getIntent().getLongExtra(EXTRA_MOVIE_ID, -1);
        mMovieRepository.retrieveMovieVideos(movieId, this);
    }

    @Override
    public void videosRetrievedSuccessfully(final List<Video> videos) {
        if (videos == null || videos.isEmpty()) {
            Toast.makeText(YoutubeActivity.this, "No Videos to play", Toast.LENGTH_LONG).show();
            finish();
        }
        final String videoKey = getVideoKeyToPlay(videos);
        playVideo(videoKey);
    }

    @Override
    public void videosRetrievalFailed() {
        Toast.makeText(YoutubeActivity.this, "Video Retrieval Failed!", Toast.LENGTH_LONG).show();
        finish();
    }

    private String getVideoKeyToPlay(final List<Video> videos) {
        // get the first video
        return videos.get(0).getKey();
    }

    private void playVideo(final String videoKey) {
        mYouTubePlayerView.initialize(BuildConfig.YOUTUBE_API_TOKEN,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(final YouTubePlayer.Provider provider,
                                                        final YouTubePlayer youTubePlayer, final boolean b) {
                        youTubePlayer.loadVideo(videoKey);
                    }

                    @Override
                    public void onInitializationFailure(final YouTubePlayer.Provider provider,
                                                        final YouTubeInitializationResult youTubeInitializationResult) {
                        Toast.makeText(YoutubeActivity.this, "Youtube Failed!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }
}
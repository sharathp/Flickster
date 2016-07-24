package com.sharathp.flickster.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.sharathp.flickster.BuildConfig;
import com.sharathp.flickster.FlicksterApplication;
import com.sharathp.flickster.R;
import com.sharathp.flickster.models.Video;
import com.sharathp.flickster.repositories.MovieRepository;
import com.sharathp.flickster.util.MovieUtil;

import java.util.List;

import butterknife.BindView;

public abstract class BaseYoutubeActivity extends YouTubeBaseActivity implements MovieRepository.MovieVideosCallback  {
    public static final String EXTRA_MOVIE_ID = YoutubeActivity.class.getSimpleName() + ":MOVIE_ID";

    private MovieRepository mMovieRepository;
    private long mMovieId;

    @BindView(R.id.ypv_play)
    YouTubePlayerView mYouTubePlayerView;

    protected static void populateIntent(final Intent intent, final long movieId) {
        intent.putExtra(EXTRA_MOVIE_ID, movieId);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovieRepository = FlicksterApplication.from(this).getComponent().getMovieRepository();
        mMovieId = getIntent().getLongExtra(EXTRA_MOVIE_ID, -1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMovieRepository.retrieveMovieVideos(mMovieId, this);
    }

    @Override
    public void videosRetrievedSuccessfully(final List<Video> videos) {
        if (videos == null || videos.isEmpty()) {
            Toast.makeText(BaseYoutubeActivity.this, "No Videos to play", Toast.LENGTH_LONG).show();
            return;
        }
        final String videoKey = MovieUtil.getVideoKeyToPlay(videos);
        playVideo(videoKey);
    }

    @Override
    public void videosRetrievalFailed() {
        Toast.makeText(BaseYoutubeActivity.this, "Video Retrieval Failed!", Toast.LENGTH_LONG).show();
    }

    private void playVideo(final String videoKey) {
        mYouTubePlayerView.initialize(BuildConfig.YOUTUBE_API_TOKEN,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(final YouTubePlayer.Provider provider,
                                                        final YouTubePlayer youTubePlayer, final boolean b) {
                        if (autoPlayVideo()) {
                            youTubePlayer.loadVideo(videoKey);
                        } else {
                            youTubePlayer.cueVideo(videoKey);
                        }
                    }

                    @Override
                    public void onInitializationFailure(final YouTubePlayer.Provider provider,
                                                        final YouTubeInitializationResult youTubeInitializationResult) {
                        Toast.makeText(BaseYoutubeActivity.this, "Youtube Failed!", Toast.LENGTH_LONG).show();
                    }
                });
    }

    // no auto-play by default
    protected boolean autoPlayVideo() {
        return false;
    }
}
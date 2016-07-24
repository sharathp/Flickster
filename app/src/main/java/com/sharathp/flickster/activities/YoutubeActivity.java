package com.sharathp.flickster.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sharathp.flickster.R;

import butterknife.ButterKnife;

public class YoutubeActivity extends BaseYoutubeActivity {

    public static Intent createIntent(final Context context, final long movieId) {
        final Intent intent = new Intent(context, YoutubeActivity.class);
        populateIntent(intent, movieId);
        return intent;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        ButterKnife.bind(this);
    }

    @Override
    public boolean autoPlayVideo() {
        return true;
    }
}
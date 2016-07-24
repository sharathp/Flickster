package com.sharathp.flickster.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.sharathp.flickster.FlicksterApplication;
import com.sharathp.flickster.repositories.MovieRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final FlicksterApplication mApplication;

    public ApplicationModule(final FlicksterApplication application) {
        mApplication = application;
    }

    @Singleton
    @NonNull
    @Provides
    Context provideApplicationContext() {
        return mApplication;
    }

    @Provides
    @NonNull
    @Singleton
    MovieRepository provideMovieRepository() {
        return new MovieRepository();
    }
}

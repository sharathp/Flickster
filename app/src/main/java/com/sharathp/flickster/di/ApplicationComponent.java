package com.sharathp.flickster.di;

import com.sharathp.flickster.di.modules.ApplicationModule;
import com.sharathp.flickster.repositories.MovieRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    MovieRepository getMovieRepository();
}
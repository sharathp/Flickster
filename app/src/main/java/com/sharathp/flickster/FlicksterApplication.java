package com.sharathp.flickster;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.sharathp.flickster.di.ApplicationComponent;
import com.sharathp.flickster.di.DaggerApplicationComponent;
import com.sharathp.flickster.di.modules.ApplicationModule;

public class FlicksterApplication extends Application {
    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDependencyInjection();
    }

    private void initDependencyInjection() {
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }

    public static FlicksterApplication from(@NonNull final Context context) {
        return (FlicksterApplication) context.getApplicationContext();
    }
}

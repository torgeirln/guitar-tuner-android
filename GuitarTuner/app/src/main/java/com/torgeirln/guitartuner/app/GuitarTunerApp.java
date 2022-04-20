package com.torgeirln.guitartuner.app;

import android.app.Application;

import androidx.annotation.NonNull;

import com.torgeirln.guitartuner.app.injection.components.AppComponent;
import com.torgeirln.guitartuner.app.injection.components.DaggerAppComponent;

import timber.log.Timber;

public class GuitarTunerApp extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Timber, for logging
        Timber.plant(new Timber.DebugTree());

        // Dagger, for dependency injection
        appComponent = DaggerAppComponent.factory().create(getApplicationContext());
    }

    @NonNull
    public AppComponent getAppComponent() {
        return appComponent;
    }

}
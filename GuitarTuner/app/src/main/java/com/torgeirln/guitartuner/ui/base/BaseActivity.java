package com.torgeirln.guitartuner.ui.base;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.torgeirln.guitartuner.app.GuitarTunerApp;
import com.torgeirln.guitartuner.app.injection.components.AppComponent;

public class BaseActivity extends AppCompatActivity {
    private AppComponent appComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = ((GuitarTunerApp) getApplication()).getAppComponent();
        }
        return appComponent;
    }

    protected void showFragment(@NonNull Fragment fragment, @IdRes int containerId) {
        getSupportFragmentManager().beginTransaction()
                .replace(containerId, fragment)
                .commit();
    }

}
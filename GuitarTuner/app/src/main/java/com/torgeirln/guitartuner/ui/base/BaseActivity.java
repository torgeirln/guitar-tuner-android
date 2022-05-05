package com.torgeirln.guitartuner.ui.base;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.torgeirln.guitartuner.app.GuitarTunerApp;
import com.torgeirln.guitartuner.app.injection.components.AppComponent;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private AppComponent appComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAppComponent().inject(this);
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

    @NonNull
    public <T extends ViewModel> T getViewModel(@NonNull Class<T> modelClass) {
        T viewModel = new ViewModelProvider(getViewModelStore(), viewModelProviderFactory).get(modelClass);
        if (viewModel instanceof BaseViewModel2) {
            ((BaseViewModel2) viewModel).bindTo(getLifecycle());
        }
        return viewModel;
    }

}
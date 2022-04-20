package com.torgeirln.guitartuner.app.injection.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.torgeirln.guitartuner.app.injection.annotations.ViewModelKey;
import com.torgeirln.guitartuner.ui.base.ViewModelProviderFactory;
import com.torgeirln.guitartuner.ui.tuner.TunerViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(TunerViewModel.class)
    public abstract ViewModel bindTunerViewModel(TunerViewModel viewModel);

}
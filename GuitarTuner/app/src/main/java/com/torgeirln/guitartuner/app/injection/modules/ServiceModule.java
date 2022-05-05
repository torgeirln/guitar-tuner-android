package com.torgeirln.guitartuner.app.injection.modules;

import com.torgeirln.guitartuner.domain.contracts.ITunerService;
import com.torgeirln.guitartuner.service.tuner.TunerService;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ServiceModule {

    @Binds
    public abstract ITunerService bindsIRecorderService(TunerService recorderService);

}
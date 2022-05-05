package com.torgeirln.guitartuner.service.tuner;

import com.torgeirln.guitartuner.domain.contracts.ITunerService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TunerService implements ITunerService {
    static {
        System.loadLibrary("tuner-lib");
    }

    @Inject
    public TunerService() {
    }

    @Override
    public void start() {
        startAudioEngineNative();
    }

    @Override
    public void stop() {
        stopAudioEngineNative();
    }

    private native void startAudioEngineNative();

    private native void stopAudioEngineNative();

}

package com.torgeirln.guitartuner.app.injection.modules;

import android.annotation.SuppressLint;
import android.media.AudioRecord;

import com.torgeirln.guitartuner.domain.contracts.IRecorderService;
import com.torgeirln.guitartuner.service.recorder.RecorderService;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ServiceModule {
    @Provides
    @Singleton
    public static RecorderService providesRecorderService() {
        // Suppress MissingPermission ok because if code reaches here permission is granted.
        @SuppressLint("MissingPermission") AudioRecord audioRecord = new AudioRecord(
                RecorderService.AUDIO_SOURCE,
                RecorderService.SAMPLING_RATE_IN_HZ,
                RecorderService.CHANNEL_CONFIG,
                RecorderService.AUDIO_FORMAT,
                RecorderService.BUFFER_SIZE
        );
        return new RecorderService(audioRecord);
    }

    @Binds
    public abstract IRecorderService bindsIRecorderService(RecorderService recorderService);

}
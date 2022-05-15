package com.torgeirln.guitartuner.service.tuner;

import androidx.annotation.NonNull;

import com.torgeirln.guitartuner.domain.contracts.ITunerService;
import com.torgeirln.guitartuner.domain.models.Frequency;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;

@Singleton
public class TunerService implements ITunerService {
    private static final int BUFFER_SIZE = 4; // 1 float
    private static final ByteBuffer CURRENT_FREQUENCY_BUFFER;

    static {
        System.loadLibrary("tuner-lib");
        CURRENT_FREQUENCY_BUFFER = ByteBuffer.allocateDirect(BUFFER_SIZE).order(ByteOrder.nativeOrder());
    }

    private final ScheduledExecutorService executorService;
    private final Subject<Frequency> currentFrequencyStream;
    private final Subject<Frequency> targetFrequencyStream;

    @Inject
    public TunerService() {
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        this.currentFrequencyStream = BehaviorSubject.create();
        this.targetFrequencyStream = BehaviorSubject.create();
    }

    @Override
    public void start() {
        executorService.scheduleAtFixedRate(
                this::updateCache,
                0,
                100,
                TimeUnit.MILLISECONDS
        );
        startAudioEngineNative();
        targetFrequencyStream.onNext(new Frequency(440f)); //TODO: Temp. Remove.
    }

    @Override
    public void stop() {
        executorService.shutdown();
        stopAudioEngineNative();
    }

    @NonNull
    @Override
    public Flowable<Frequency> getCurrentFrequencyStream() {
        return currentFrequencyStream.toFlowable(BackpressureStrategy.LATEST).distinctUntilChanged();
    }

    @NonNull
    @Override
    public Flowable<Frequency> getTargetFrequencyStream() {
        return targetFrequencyStream.toFlowable(BackpressureStrategy.LATEST).distinctUntilChanged();
    }

    private void updateCache() {
        getCurrentFrequencyNative(CURRENT_FREQUENCY_BUFFER);

        CURRENT_FREQUENCY_BUFFER.rewind();
        float freq = CURRENT_FREQUENCY_BUFFER.getFloat();

        currentFrequencyStream.onNext(new Frequency(freq));

    }

    private native void startAudioEngineNative();

    private native void stopAudioEngineNative();

    private native void getCurrentFrequencyNative(ByteBuffer byteBuffer);

}

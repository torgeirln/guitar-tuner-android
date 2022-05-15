package com.torgeirln.guitartuner.domain.contracts;

import androidx.annotation.NonNull;

import com.torgeirln.guitartuner.domain.models.Frequency;

import io.reactivex.rxjava3.core.Flowable;

public interface ITunerService {

    void start();

    void stop();

    @NonNull
    Flowable<Frequency> getCurrentFrequencyStream();

    @NonNull
    Flowable<Frequency> getTargetFrequencyStream();

}

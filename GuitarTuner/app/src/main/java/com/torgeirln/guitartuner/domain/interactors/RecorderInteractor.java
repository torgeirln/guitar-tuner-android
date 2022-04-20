package com.torgeirln.guitartuner.domain.interactors;

import com.torgeirln.guitartuner.domain.contracts.IRecorderService;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import timber.log.Timber;

public class RecorderInteractor {
    private final IRecorderService recorderService;

    @Inject
    public RecorderInteractor(@NonNull IRecorderService recorderService) {
        this.recorderService = recorderService;
    }

    @NonNull
    public Flowable<Integer> getFrequencyStream() {
        Timber.d("getFrequencyStream: ");
        return Flowable.just(400).distinctUntilChanged();
    }

    @NonNull
    public Flowable<Integer> getTargetStream() {
        return Flowable.just(440);
    }

}
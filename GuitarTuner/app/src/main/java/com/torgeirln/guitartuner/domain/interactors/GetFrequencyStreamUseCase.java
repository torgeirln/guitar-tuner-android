package com.torgeirln.guitartuner.domain.interactors;

import androidx.annotation.NonNull;

import com.torgeirln.guitartuner.domain.base.AsyncUseCaseOut;
import com.torgeirln.guitartuner.domain.contracts.ITunerService;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

public class GetFrequencyStreamUseCase extends AsyncUseCaseOut<Flowable<Integer>> {
    private final ITunerService tunerService;

    @Inject
    public GetFrequencyStreamUseCase(@NonNull ITunerService tunerService) {
        this.tunerService = tunerService;
    }

    @NonNull
    @Override
    protected Flowable<Integer> executeAsync() {
        return Flowable.just(400).distinctUntilChanged();
    }

}
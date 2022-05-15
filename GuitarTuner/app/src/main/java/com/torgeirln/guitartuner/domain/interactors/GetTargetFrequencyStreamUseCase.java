package com.torgeirln.guitartuner.domain.interactors;

import androidx.annotation.NonNull;

import com.torgeirln.guitartuner.domain.base.AsyncUseCaseOut;
import com.torgeirln.guitartuner.domain.contracts.ITunerService;
import com.torgeirln.guitartuner.domain.models.Frequency;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

public class GetTargetFrequencyStreamUseCase extends AsyncUseCaseOut<Flowable<Frequency>> {
    private final ITunerService tunerService;

    @Inject
    public GetTargetFrequencyStreamUseCase(@NonNull ITunerService tunerService) {
        this.tunerService = tunerService;
    }

    @NonNull
    @Override
    protected Flowable<Frequency> executeAsync() {
        return tunerService.getTargetFrequencyStream();
    }

}
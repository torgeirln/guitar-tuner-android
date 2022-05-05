package com.torgeirln.guitartuner.domain.interactors;

import com.torgeirln.guitartuner.domain.base.AsyncUseCase;
import com.torgeirln.guitartuner.domain.contracts.ITunerService;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;

public class StopTunerUseCase extends AsyncUseCase {
    private final ITunerService tunerService;

    @Inject
    public StopTunerUseCase(@NonNull ITunerService tunerService) {
        this.tunerService = tunerService;
    }

    @Override
    protected void executeAsync() {
        tunerService.stop();
    }

}
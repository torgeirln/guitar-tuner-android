package com.torgeirln.guitartuner.ui;

import com.torgeirln.guitartuner.domain.interactors.StartTunerUseCase;
import com.torgeirln.guitartuner.ui.base.BaseViewModel2;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;

public class MainViewModel extends BaseViewModel2 {
    private final StartTunerUseCase startTunerUseCase;

    @Inject
    public MainViewModel(@NonNull StartTunerUseCase startTunerUseCase) {
        this.startTunerUseCase = startTunerUseCase;
    }

    public void startTuner() {
        startTunerUseCase.execute();
    }

}
package com.torgeirln.guitartuner.ui.tuner;

import androidx.lifecycle.LiveData;

import com.torgeirln.guitartuner.domain.interactors.GetCurrentFrequencyStreamUseCase;
import com.torgeirln.guitartuner.domain.interactors.GetTargetFrequencyStreamUseCase;
import com.torgeirln.guitartuner.domain.models.Frequency;
import com.torgeirln.guitartuner.ui.base.BaseViewModel2;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;

public class TunerViewModel extends BaseViewModel2 {
    private final GetCurrentFrequencyStreamUseCase getCurrentFrequencyStreamUseCase;
    private final GetTargetFrequencyStreamUseCase getTargetFrequencyStreamUseCase;

    @Inject
    public TunerViewModel(
            @NonNull GetCurrentFrequencyStreamUseCase getCurrentFrequencyStreamUseCase,
            @NonNull GetTargetFrequencyStreamUseCase getTargetFrequencyStreamUseCase
    ) {
        this.getCurrentFrequencyStreamUseCase = getCurrentFrequencyStreamUseCase;
        this.getTargetFrequencyStreamUseCase = getTargetFrequencyStreamUseCase;
    }

    @NonNull
    public LiveData<Frequency> getCurrentFrequencyLiveData() {
        return getLiveDataFrom(getCurrentFrequencyStreamUseCase::execute);
    }

    @NonNull
    public LiveData<Frequency> getTargetFrequencyLiveData() {
        return getLiveDataFrom(getTargetFrequencyStreamUseCase::execute);
    }

}
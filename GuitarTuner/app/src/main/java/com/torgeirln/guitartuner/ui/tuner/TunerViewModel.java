package com.torgeirln.guitartuner.ui.tuner;

import androidx.lifecycle.LiveData;

import com.torgeirln.guitartuner.domain.interactors.GetFrequencyStreamUseCase;
import com.torgeirln.guitartuner.domain.interactors.GetTargetStreamUseCase;
import com.torgeirln.guitartuner.ui.base.BaseViewModel2;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import timber.log.Timber;

public class TunerViewModel extends BaseViewModel2 {
    private final GetFrequencyStreamUseCase getFrequencyStreamUseCase;
    private final GetTargetStreamUseCase getTargetStreamUseCase;

    @Inject
    public TunerViewModel(
            @NonNull GetFrequencyStreamUseCase getFrequencyStreamUseCase,
            @NonNull GetTargetStreamUseCase getTargetStreamUseCase
    ) {
        this.getFrequencyStreamUseCase = getFrequencyStreamUseCase;
        this.getTargetStreamUseCase = getTargetStreamUseCase;
    }

    @NonNull
    public LiveData<Integer> getFrequencyLiveData() { //TODO: Do not send integer. Better name?
        Timber.d("getFrequencyLiveData: ");
        return getLiveDataFrom(getFrequencyStreamUseCase::execute);
    }

    @NonNull
    public LiveData<Integer> getTargetLiveData() {
        return getLiveDataFrom(getTargetStreamUseCase::execute);
    }

}
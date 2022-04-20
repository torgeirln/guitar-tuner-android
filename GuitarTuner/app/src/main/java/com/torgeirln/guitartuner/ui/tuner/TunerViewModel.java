package com.torgeirln.guitartuner.ui.tuner;

import androidx.lifecycle.LiveData;

import com.torgeirln.guitartuner.domain.interactors.RecorderInteractor;
import com.torgeirln.guitartuner.ui.base.BaseViewModel;
import com.torgeirln.guitartuner.ui.base.BaseViewModel2;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import timber.log.Timber;

public class TunerViewModel extends BaseViewModel2 {
    private final RecorderInteractor recorderInteractor;

    @Inject
    public TunerViewModel(@NonNull RecorderInteractor recorderInteractor) {
        this.recorderInteractor = recorderInteractor;
    }

    @NonNull
    public LiveData<Integer> getFrequencyLiveData() { //TODO: Do not send integer. Better name?
        Timber.d("getFrequencyLiveData: ");
        return getLiveDataFrom(recorderInteractor::getFrequencyStream);
    }

    @NonNull
    public LiveData<Integer> getTargetLiveData() {
        return getLiveDataFrom(recorderInteractor::getTargetStream);
    }

}
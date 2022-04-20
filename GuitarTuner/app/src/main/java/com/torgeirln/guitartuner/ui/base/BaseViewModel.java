package com.torgeirln.guitartuner.ui.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.core.Flowable;

public class BaseViewModel extends ViewModel {

    public BaseViewModel() {
    }

    @NonNull
    public <T> LiveData<T> getLiveDataFrom(@NonNull Flowable<T> stream) {
        return LiveDataReactiveStreams.fromPublisher(stream);
    }

}
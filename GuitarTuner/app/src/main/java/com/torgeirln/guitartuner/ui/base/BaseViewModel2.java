package com.torgeirln.guitartuner.ui.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import timber.log.Timber;

public class BaseViewModel2 extends ViewModel implements DefaultLifecycleObserver {
    protected final CompositeDisposable compositeDisposable;
    private final Map<Integer, LiveData<?>> liveDataMap;

    private int liveDataId;

    public BaseViewModel2() {
        this.compositeDisposable = new CompositeDisposable();
        this.liveDataMap = new HashMap<>();
    }

    /**
     * Bind the view model to the lifecycle of an activity or a fragment.
     */
    public void bindTo(@NonNull Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    /**
     * Add disposable to CompositeDisposable. The disposable will be disposed automatically
     * when the bound lifecycle enters onDestroy().
     */
    protected void addDisposable(@NonNull Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        Timber.d("onCreate: ");
        liveDataId = 0;
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        compositeDisposable.clear();
    }

    @NonNull
    protected <T> LiveData<T> getLiveDataFrom(@NonNull Callable<Flowable<T>> getStreamTask) {
        LiveData<T> liveData;
        LiveData<?> storedLiveData = liveDataMap.get(liveDataId);
        if (storedLiveData == null) {
            Timber.d("getLiveDataFrom: Store is null! %s", liveDataId);
            LiveData<T> liveDataFromStream = getLiveDataFromStream(getStreamTask);
            liveDataMap.put(liveDataId, liveDataFromStream);
            liveData = liveDataFromStream;
        } else {
            Timber.d("getLiveDataFrom: stored is NOT null! %s", liveDataId);
            try {
                // Suppressing "unchecked cast" ok b.c. of try-catch
                //noinspection unchecked
                liveData = (LiveData<T>) storedLiveData;
            } catch (ClassCastException e) {
                Timber.w(e, "getLiveDataFrom: Failed to cast stored LiveData, creating new LiveData from stream.");
                liveData = getLiveDataFromStream(getStreamTask);
            }
        }
        liveDataId++; // Increment for the next getLiveDataFrom(.)-request.
        return liveData;
    }

    @NonNull
    private <T> LiveData<T> getLiveDataFromStream(@NonNull Callable<Flowable<T>> getStreamTask) {
        try {
            return LiveDataReactiveStreams.fromPublisher(getStreamTask.call());
        } catch (Exception e) {
            Timber.e(e, "getLiveDataFromStream: Returning empty live data.");
            return new MutableLiveData<>();
        }
    }

}

package com.torgeirln.guitartuner.domain.base;

import androidx.annotation.NonNull;

public abstract class AsyncUseCaseInOut<Args, Result> {

    @NonNull
    protected abstract Result executeAsync(@NonNull Args args);

    @NonNull
    public Result execute(@NonNull Args args) {
        return executeAsync(args); //TODO: Execute async
    }

}
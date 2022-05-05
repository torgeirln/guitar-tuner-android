package com.torgeirln.guitartuner.domain.base;

import androidx.annotation.NonNull;

public abstract class AsyncUseCaseOut<Result> {

    @NonNull
    protected abstract Result executeAsync();

    @NonNull
    public Result execute() {
        return executeAsync(); //TODO: Execute async
    }

}
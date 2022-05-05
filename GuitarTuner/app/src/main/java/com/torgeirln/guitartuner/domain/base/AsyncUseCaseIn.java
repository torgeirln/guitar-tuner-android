package com.torgeirln.guitartuner.domain.base;

import androidx.annotation.NonNull;

public abstract class AsyncUseCaseIn<Args> {

    protected abstract void executeAsync(@NonNull Args args);

    public void execute(@NonNull Args args) {
        executeAsync(args); //TODO: Execute async
    }
}
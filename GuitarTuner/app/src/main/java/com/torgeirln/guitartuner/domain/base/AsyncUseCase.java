package com.torgeirln.guitartuner.domain.base;

public abstract class AsyncUseCase {

    protected abstract void executeAsync();

    public void execute() {
        executeAsync(); //TODO: Execute async
    }

}
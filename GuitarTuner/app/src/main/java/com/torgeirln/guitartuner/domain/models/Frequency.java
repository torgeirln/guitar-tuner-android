package com.torgeirln.guitartuner.domain.models;

import androidx.annotation.Nullable;

public class Frequency {
    @Nullable
    public final Float value;

    public Frequency(@Nullable Float value) {
        this.value = value;
    }
}

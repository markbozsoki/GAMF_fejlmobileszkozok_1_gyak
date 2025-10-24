package com.nje.mobileszkozokprojekt.model;

import androidx.annotation.NonNull;

public enum Direction {
    INCOMING("incoming"),
    OUTGOING("outgoing");

    public final String value;

    Direction(@NonNull String value) {
        this.value = value.toLowerCase();
    }

    @NonNull
    @Override
    public String toString() {
        return this.value;
    }
}

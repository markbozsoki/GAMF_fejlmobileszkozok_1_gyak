package com.nje.mobileszkozokprojekt.model.acquired;

import androidx.annotation.NonNull;

public enum CategoryProvider {
    ACQUIRED("acquired"),
    OUTGOING("outgoing"),
    PROPERTY("property"),
    VEHICLE("vehicle"),
    GOODS("goods"),
    CURRENCY("currency");

    public final String value;

    CategoryProvider(@NonNull String value) {
        this.value = value.toLowerCase();
    }

    @NonNull
    @Override
    public String toString() {
        return this.value;
    }
}

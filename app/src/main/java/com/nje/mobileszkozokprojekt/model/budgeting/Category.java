package com.nje.mobileszkozokprojekt.model.budgeting;

import androidx.annotation.NonNull;

public enum Category {
    INCOME("income"),
    COST("cost"),
    DEBT("debt"),
    INVESTMENT("investment");

    public final String value;

    Category(@NonNull String value) {
        this.value = value.toLowerCase();
    }

    @NonNull
    @Override
    public String toString() {
        return this.value;
    }
}

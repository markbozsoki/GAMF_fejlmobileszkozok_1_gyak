package com.nje.mobileszkozokprojekt.model.estimation;

import androidx.annotation.NonNull;

import java.util.Objects;

public class EstimationItem {

    private String name;
    private double value;

    public EstimationItem(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EstimationItem that = (EstimationItem) o;
        return Double.compare(value, that.value) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @NonNull
    @Override
    public String toString() {
        return "EstimationItem{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}

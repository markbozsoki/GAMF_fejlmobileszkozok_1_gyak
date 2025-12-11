package com.nje.mobileszkozokprojekt.model.estimation;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class EstimationItem {

    private String name;
    private double value;
    private String date;

    public EstimationItem(
            String name,
            double value,
            String date
    ) {
        this.name = name;
        this.value = value;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    private final SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private final SimpleDateFormat sdfDisplay = new SimpleDateFormat("MMM dd", Locale.getDefault());

    public String getFormatedDate() {
        try {
            Date _date = sdfInput.parse(date);
            assert _date != null;
            return sdfDisplay.format(_date);
        } catch (ParseException e) {
            return date;
        }
    }

    public void setDate(String date) {
        this.date = date;
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

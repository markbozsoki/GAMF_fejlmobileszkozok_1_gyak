package com.nje.mobileszkozokprojekt.model.estimation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EstimationItem {

    private double value;
    private String date;

    public EstimationItem(
            double value,
            String date
    ) {
        this.value = value;
        this.date = date;
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

    public String getMonthDate() {
        return getFormatedDate().substring(0,6);
    }

    public void setDate(String date) {
        this.date = date;
    }
}

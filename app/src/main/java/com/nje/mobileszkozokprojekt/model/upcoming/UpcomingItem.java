package com.nje.mobileszkozokprojekt.model.upcoming;

import com.nje.mobileszkozokprojekt.model.Direction;

public class UpcomingItem {
    private String name;
    private Direction type;
    private double value;
    private String dueDate;

    public UpcomingItem(
            String name,
            Direction type,
            double value,
            String dueDate
    ) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Direction getType() {
        return type;
    }

    public void setType(Direction type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}

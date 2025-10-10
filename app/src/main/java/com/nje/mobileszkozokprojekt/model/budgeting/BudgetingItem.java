package com.nje.mobileszkozokprojekt.model.budgeting;

import com.nje.mobileszkozokprojekt.model.Direction;

public class BudgetingItem {
    private String name;
    private Direction type;
    private Category category;
    private double value;

    public BudgetingItem(
            String name,
            Direction type,
            Category category,
            double value
    ) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.value = value;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

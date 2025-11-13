package com.nje.mobileszkozokprojekt.model.acquired;

public class AcquiredItem {

    private String name;
    private CategoryProvider type;
    private CategoryProvider category;
    private double value;

    public AcquiredItem(String name, CategoryProvider type, CategoryProvider category, double value) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.value = value;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public CategoryProvider getType() { return type; }
    public void setType(CategoryProvider type) { this.type = type; }

    public CategoryProvider getCategory() { return category; }
    public void setCategory(CategoryProvider category) { this.category = category; }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
}

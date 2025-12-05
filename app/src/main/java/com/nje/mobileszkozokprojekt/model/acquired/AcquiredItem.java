package com.nje.mobileszkozokprojekt.model.acquired;

public class AcquiredItem {

    private int id;
    private String name;
    private CategoryProvider type;
    private CategoryProvider category;
    private double value;

    public AcquiredItem(int id, String name, CategoryProvider type, CategoryProvider category, double value) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.category = category;
        this.value = value;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public CategoryProvider getType() { return type; }
    public void setType(CategoryProvider type) { this.type = type; }

    public CategoryProvider getCategory() { return category; }
    public void setCategory(CategoryProvider category) { this.category = category; }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
}

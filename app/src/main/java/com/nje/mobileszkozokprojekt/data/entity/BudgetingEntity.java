package com.nje.mobileszkozokprojekt.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.nje.mobileszkozokprojekt.data.entity.interfaces.IEntityWithId;

@Entity(tableName = "budgeting")
public class BudgetingEntity implements IEntityWithId {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String type;
    private String category;
    private double value; // in USD

    public BudgetingEntity(
            int id,
            String name,
            String type,
            String category,
            double value
    ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.category = category;
        this.value = value;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

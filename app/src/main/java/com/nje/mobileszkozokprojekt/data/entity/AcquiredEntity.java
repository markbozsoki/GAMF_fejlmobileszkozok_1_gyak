package com.nje.mobileszkozokprojekt.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.nje.mobileszkozokprojekt.data.entity.interfaces.IEntityWithId;

@Entity(tableName = "acquired")
public class AcquiredEntity implements IEntityWithId {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String category;
    private double value;
    private String currency;

    public AcquiredEntity(
            int id,
            String name,
            String category,
            double value,
            String currency
    ) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.value = value;
        this.currency = currency;
    }

    @Override
    public int getId() {return id; }

    @Override
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public double getValue() { return value; }

    public void setValue(double value) { this.value = value; }

    private String getCurrency() {return currency; }

    public void setCurrency(String currency) { this.currency = currency; }
}

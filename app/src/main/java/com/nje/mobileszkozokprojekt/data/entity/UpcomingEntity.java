package com.nje.mobileszkozokprojekt.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.nje.mobileszkozokprojekt.data.entity.interfaces.IEntityWithId;

@Entity(tableName = "upcoming")
public class UpcomingEntity implements IEntityWithId {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String type;
    private double value; // in USD
    private String dueDate;

    public UpcomingEntity(
            int id,
            String name,
            String type,
            double value,
            String dueDate
    ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
        this.dueDate = dueDate;
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

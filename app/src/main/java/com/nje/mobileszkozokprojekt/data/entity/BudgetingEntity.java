package com.nje.mobileszkozokprojekt.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.nje.mobileszkozokprojekt.data.entity.interfaces.IEntityWithId;

@Entity(tableName = "budgeting")
public class BudgetingEntity implements IEntityWithId {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public BudgetingEntity(
            int id
    ) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}

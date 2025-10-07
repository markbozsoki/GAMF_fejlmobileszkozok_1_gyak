package com.nje.mobileszkozokprojekt.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nje.mobileszkozokprojekt.data.entity.BudgetingEntity;

import java.util.List;

@Dao
public interface BudgetingDao extends DefaultDao<BudgetingEntity> {

    @Query("SELECT * FROM budgeting")
    List<BudgetingEntity> getAll();

    @Query("SELECT * FROM budgeting WHERE id = :id")
    BudgetingEntity getById(String id);
}

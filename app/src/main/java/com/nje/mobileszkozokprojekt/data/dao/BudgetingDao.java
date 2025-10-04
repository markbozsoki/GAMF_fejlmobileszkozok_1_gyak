package com.nje.mobileszkozokprojekt.data.dao;

import com.nje.mobileszkozokprojekt.data.entity.BudgetingEntity;

import java.util.List;

public interface BudgetingDao {

    List<BudgetingEntity> getAll();

    BudgetingEntity getById(String id);

    /**
     * Returns true on success, returns false on error
     */
    boolean insert(BudgetingEntity entity);

    /**
     * Returns true on success, returns false on error
     */
    boolean update(BudgetingEntity entity);

    /**
     * Returns true on success, returns false on error
     */
    boolean delete(BudgetingEntity entity);
}

package com.nje.mobileszkozokprojekt.data.repository;

import com.nje.mobileszkozokprojekt.data.entity.BudgetingEntity;
import com.nje.mobileszkozokprojekt.data.repository.interfaces.IRepository;

import java.util.Collections;
import java.util.List;

public class BudgetingRepository implements IRepository<BudgetingEntity> {

    @Override
    public List<BudgetingEntity> getAll() {
        return Collections.emptyList();
    }

    @Override
    public BudgetingEntity getById(String id) {
        return null;
    }

    @Override
    public boolean insert(BudgetingEntity entity) {
        return false;
    }

    @Override
    public boolean update(BudgetingEntity entity) {
        return false;
    }

    @Override
    public boolean delete(BudgetingEntity entity) {
        return false;
    }
}

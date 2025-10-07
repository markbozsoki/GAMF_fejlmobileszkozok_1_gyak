package com.nje.mobileszkozokprojekt.data.repository;

import com.nje.mobileszkozokprojekt.data.FinDatabase;
import com.nje.mobileszkozokprojekt.data.entity.BudgetingEntity;
import com.nje.mobileszkozokprojekt.data.repository.interfaces.IRepository;

import java.util.Collections;
import java.util.List;

public class BudgetingRepository implements IRepository<BudgetingEntity> {

    private final FinDatabase database;

    public BudgetingRepository(FinDatabase database) {
        this.database = database;
    }

    @Override
    public List<BudgetingEntity> getAll() {
        return database.budgetingDao().getAll();
    }

    @Override
    public BudgetingEntity getById(String id) {
        return database.budgetingDao().getById(id);
    }

    @Override
    public void insert(BudgetingEntity entity) {
        database.budgetingDao().insert(entity);
    }

    @Override
    public void update(BudgetingEntity entity) {
        database.budgetingDao().update(entity);
    }

    @Override
    public void delete(BudgetingEntity entity) {
        database.budgetingDao().delete(entity);
    }
}

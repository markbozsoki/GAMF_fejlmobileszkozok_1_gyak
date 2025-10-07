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
        try {
            return database.budgetingDao().getAll();
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    @Override
    public BudgetingEntity getById(String id) {
        try {
            return database.budgetingDao().getById(id);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean insert(BudgetingEntity entity) {
        try {
            database.budgetingDao().insert(entity);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean update(BudgetingEntity entity) {
        try {
            database.budgetingDao().update(entity);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(BudgetingEntity entity) {
        try {
            database.budgetingDao().delete(entity);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}

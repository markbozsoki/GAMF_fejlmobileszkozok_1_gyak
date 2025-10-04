package com.nje.mobileszkozokprojekt.data.dao;

import com.nje.mobileszkozokprojekt.data.entity.BudgetingEntity;

import java.util.List;

public interface BudgetingDao extends DefaultDao<BudgetingEntity> {

    List<BudgetingEntity> getAll();

    BudgetingEntity getById(String id);
}

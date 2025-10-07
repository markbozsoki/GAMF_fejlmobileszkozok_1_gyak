package com.nje.mobileszkozokprojekt.data;

import com.nje.mobileszkozokprojekt.data.entity.BudgetingEntity;

public class DatabaseInitializer {

    private final FinDatabase dataBase;

    public DatabaseInitializer(FinDatabase dataBase) {
        this.dataBase = dataBase;
    }

    public void populateDatabase() {
        seedBudgetingEntities();
    }

    private void seedBudgetingEntities(){
        dataBase.budgetingDao().insert(new BudgetingEntity(1));
    }
}

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
        dataBase.budgetingDao().insert(new BudgetingEntity(1, "salary", "incoming", "income", 350.57));
        dataBase.budgetingDao().insert(new BudgetingEntity(2, "grocery", "outgoing", "cost", 200));
        dataBase.budgetingDao().insert(new BudgetingEntity(3, "car payment", "outgoing", "debt", 135.48));
        dataBase.budgetingDao().insert(new BudgetingEntity(4, "EFT investing", "outgoing", "investment", 50));
    }
}

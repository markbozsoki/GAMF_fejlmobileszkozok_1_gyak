package com.nje.mobileszkozokprojekt.data;

import com.nje.mobileszkozokprojekt.data.entity.AcquiredEntity;
import com.nje.mobileszkozokprojekt.data.entity.BudgetingEntity;
import com.nje.mobileszkozokprojekt.data.entity.UpcomingEntity;

public class DatabaseInitializer {

    private final FinDatabase dataBase;

    public DatabaseInitializer(FinDatabase dataBase) {
        this.dataBase = dataBase;
    }

    public void clearDatabase() {
        dataBase.databaseDao().clearAllItemsFromDatabase();
    }

    public void populateDatabase() {
        seedBudgetingEntities();
        seedUpcomingEntities();
        seedAcquiredEntities();
    }

    private void seedBudgetingEntities(){
        dataBase.budgetingDao().insert(new BudgetingEntity(1, "salary", "incoming", "income", 350.57));
        dataBase.budgetingDao().insert(new BudgetingEntity(2, "grocery", "outgoing", "cost", 200));
        dataBase.budgetingDao().insert(new BudgetingEntity(3, "car payment", "outgoing", "debt", 135.48));
        dataBase.budgetingDao().insert(new BudgetingEntity(4, "EFT investing", "outgoing", "investment", 50));
        dataBase.budgetingDao().insert(new BudgetingEntity(6, "concert ticket", "outgoing", "cost", 15));
        dataBase.budgetingDao().insert(new BudgetingEntity(8, "stock investing", "outgoing", "investment", 50));
    }

    private void seedUpcomingEntities(){
        dataBase.upcomingDao().insert(new UpcomingEntity(1, "tax return", "incoming", 407, "2026-05-01"));
        dataBase.upcomingDao().insert(new UpcomingEntity(2, "insurance", "outgoing", 160, "2026-10-12"));
        dataBase.upcomingDao().insert(new UpcomingEntity(3, "EFT profit", "incoming", 11.02, "2025-12-31"));
        dataBase.upcomingDao().insert(new UpcomingEntity(4, "property tax", "outgoing", 80.53, "2026-04-30"));
    }

    private void seedAcquiredEntities(){
        dataBase.acquiredDao().insert(new AcquiredEntity(1, "house", "property", "acquired", 200000));
        dataBase.acquiredDao().insert(new AcquiredEntity(2, "car", "goods", "acquired", 20000));
        dataBase.acquiredDao().insert(new AcquiredEntity(3, "gold chain", "heritage", "acquired", 300.5));
        dataBase.acquiredDao().insert(new AcquiredEntity(4, "laptop", "goods", "acquired" ,1000));
    }
}

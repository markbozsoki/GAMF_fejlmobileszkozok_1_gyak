package com.nje.mobileszkozokprojekt.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.nje.mobileszkozokprojekt.data.dao.BudgetingDao;
import com.nje.mobileszkozokprojekt.data.entity.BudgetingEntity;

import java.util.concurrent.Executors;

@Database(entities = {
        BudgetingEntity.class
}, version = 1)
public abstract class FinDatabase extends RoomDatabase {

    public abstract BudgetingDao budgetingDao();

    public static volatile FinDatabase INSTANCE;

    public static FinDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (FinDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            FinDatabase.class,
                            "fin_database"
                    ).addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadExecutor().execute(() -> {
                                FinDatabase dataBase = getInstance(context);
                                DatabaseInitializer initializer = new DatabaseInitializer(dataBase);
                                initializer.populateDatabase();
                            });
                        }
                    }).allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}

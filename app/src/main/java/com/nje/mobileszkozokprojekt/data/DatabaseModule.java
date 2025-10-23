package com.nje.mobileszkozokprojekt.data;

import android.content.Context;

import com.nje.mobileszkozokprojekt.data.entity.BudgetingEntity;
import com.nje.mobileszkozokprojekt.data.repository.BudgetingRepository;
import com.nje.mobileszkozokprojekt.data.repository.interfaces.IRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public FinDatabase provideFinDatabase(@ApplicationContext Context context) {
        return FinDatabase.getInstance(context);
    }

    @Provides
    @Singleton
    public IRepository<BudgetingEntity> provideBudgetingRepository(FinDatabase database) {
        return new BudgetingRepository(database);
    }
}

package com.nje.mobileszkozokprojekt.data.repository;

import com.nje.mobileszkozokprojekt.data.FinDatabase;
import com.nje.mobileszkozokprojekt.data.entity.UpcomingEntity;
import com.nje.mobileszkozokprojekt.data.repository.interfaces.IRepository;

import java.util.List;

public class UpcomingRepository implements IRepository<UpcomingEntity> {

    private final FinDatabase database;

    public UpcomingRepository(FinDatabase database) {
        this.database = database;
    }

    @Override
    public List<UpcomingEntity> getAll() {
        return database.upcomingDao().getAll();
    }

    @Override
    public UpcomingEntity getById(String id) {
        return database.upcomingDao().getById(id);
    }

    @Override
    public void insert(UpcomingEntity entity) {
        database.upcomingDao().insert(entity);
    }

    @Override
    public void update(UpcomingEntity entity) {
        database.upcomingDao().update(entity);
    }

    @Override
    public void delete(UpcomingEntity entity) {
        database.upcomingDao().delete(entity);
    }
}

package com.nje.mobileszkozokprojekt.data.repository;

import com.nje.mobileszkozokprojekt.data.FinDatabase;
import com.nje.mobileszkozokprojekt.data.entity.AcquiredEntity;
import com.nje.mobileszkozokprojekt.data.repository.interfaces.IRepository;

import java.util.List;

public class AcquiredRepository implements IRepository<AcquiredEntity> {

    private final FinDatabase database;
    public AcquiredRepository(FinDatabase database) { this.database = database; }
    @Override
    public List<AcquiredEntity> getAll() { return database.acquiredDao().getAll(); }
    @Override
    public AcquiredEntity getById(String id) { return database.acquiredDao().getById(id); }
    @Override
    public void insert(AcquiredEntity entity) { database.acquiredDao().insert(entity); }
    @Override
    public void update(AcquiredEntity entity) { database.acquiredDao().update(entity); }
    @Override
    public void delete(AcquiredEntity entity) { database.acquiredDao().delete(entity); }
}

package com.nje.mobileszkozokprojekt.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nje.mobileszkozokprojekt.data.entity.AcquiredEntity;

import java.util.List;
@Dao
public interface AcquiredDao extends DefaultDao<AcquiredEntity> {

    @Query("SELECT * FROM acquired")
    List<AcquiredEntity> getAll();

    @Query("SELECT * FROM acquired WHERE id = :id")
    AcquiredEntity getById(String id);
}

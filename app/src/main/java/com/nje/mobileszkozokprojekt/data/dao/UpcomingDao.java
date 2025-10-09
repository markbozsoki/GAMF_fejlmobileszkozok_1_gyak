package com.nje.mobileszkozokprojekt.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nje.mobileszkozokprojekt.data.entity.UpcomingEntity;

import java.util.List;

@Dao
public interface UpcomingDao extends DefaultDao<UpcomingEntity> {

    @Query("SELECT * FROM upcoming")
    List<UpcomingEntity> getAll();

    @Query("SELECT * FROM upcoming WHERE id = :id")
    UpcomingEntity getById(String id);
}

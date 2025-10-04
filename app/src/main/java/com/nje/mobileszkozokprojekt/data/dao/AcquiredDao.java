package com.nje.mobileszkozokprojekt.data.dao;

import com.nje.mobileszkozokprojekt.data.entity.AcquiredEntity;

import java.util.List;

public interface AcquiredDao extends DefaultDao<AcquiredEntity> {

    List<AcquiredEntity> getAll();

    AcquiredEntity getById(String id);
}

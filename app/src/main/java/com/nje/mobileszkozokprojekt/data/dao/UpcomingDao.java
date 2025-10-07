package com.nje.mobileszkozokprojekt.data.dao;

import com.nje.mobileszkozokprojekt.data.entity.UpcomingEntity;

import java.util.List;

public interface UpcomingDao extends DefaultDao<UpcomingEntity> {

    List<UpcomingEntity> getAll();

    UpcomingEntity getById(String id);
}

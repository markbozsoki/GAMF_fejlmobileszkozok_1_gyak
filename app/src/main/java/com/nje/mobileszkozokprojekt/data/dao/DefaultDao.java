package com.nje.mobileszkozokprojekt.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface DefaultDao<T> {

    /**
     * Returns true on success, returns false on error
     */
    @Insert
    boolean insert(T entity);

    /**
     * Returns true on success, returns false on error
     */
    @Update
    boolean update(T entity);

    /**
     * Returns true on success, returns false on error
     */
    @Delete
    boolean delete(T entity);
}

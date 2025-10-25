package com.nje.mobileszkozokprojekt.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface DatabaseDao {

    @Query("DELETE FROM sqlite_sequence")
    void clearAllItemsFromDatabase();
}

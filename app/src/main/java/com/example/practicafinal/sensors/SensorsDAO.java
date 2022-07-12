package com.example.practicafinal.sensors;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SensorsDAO {
    @Query("SELECT * FROM sensors_data")
    LiveData<List<SensorsInfo>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SensorsInfo item);
}

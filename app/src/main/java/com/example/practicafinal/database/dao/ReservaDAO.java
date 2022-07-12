package com.example.practicafinal.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.practicafinal.database.entity.ReservaEntity;

import java.util.List;

@Dao
public interface ReservaDAO {
    @Query("select * from reservas")
    LiveData<List<ReservaEntity>> getAll();

    @Insert
    void insert(ReservaEntity group);

    @Query("delete from reservas")
    void deleteAll();

    @Delete
    void delete(ReservaEntity group);
}

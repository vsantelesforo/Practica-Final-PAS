package com.example.practicafinal.sensors;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SensorsInfo.class}, version = 1, exportSchema = false)
public abstract class SensorsDatabase extends RoomDatabase {
    public abstract SensorsDAO getSensorsInfoDAO();

    public static final String DB_NAME = "stolen_info.db";
    private static SensorsDatabase INSTANCE;
    private static final int THREADS = 4;

    public static final ExecutorService dbExecutor = Executors.newFixedThreadPool(THREADS);

    public static SensorsDatabase getInstance(final Context context){
        if(INSTANCE == null){
            synchronized (SensorsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SensorsDatabase.class, DB_NAME).build();
                }
            }
        }
        return INSTANCE;
    }
}

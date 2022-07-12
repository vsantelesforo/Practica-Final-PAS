package com.example.practicafinal.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.practicafinal.database.dao.ReservaDAO;
import com.example.practicafinal.database.entity.ReservaEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ReservaEntity.class}, version = 1, exportSchema = false)
public abstract class ReservasDatabase extends RoomDatabase{

    private static volatile ReservasDatabase INSTANCE;
    public static final String DATABASE = "reservas.db";
    public abstract ReservaDAO reservaDAO();

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ReservasDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (ReservasDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ReservasDatabase.class, DATABASE)
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);

                    // If you want to keep data through app restarts,
                    // comment out the following block
                    databaseWriteExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            // Populate the database in the background.
                            // If you want to start with more groups, just add them.
                            ReservaDAO dao = INSTANCE.reservaDAO();
                        }
                    });
                }
            };
}

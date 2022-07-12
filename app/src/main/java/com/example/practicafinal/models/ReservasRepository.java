package com.example.practicafinal.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.practicafinal.database.ReservasDatabase;
import com.example.practicafinal.database.dao.ReservaDAO;
import com.example.practicafinal.database.entity.ReservaEntity;

import java.util.List;

public class ReservasRepository {
    private final LiveData<List<ReservaEntity>> liveDataList;
    private final ReservaDAO reservaDAO;

    public ReservasRepository(Application application){
        ReservasDatabase db = ReservasDatabase.getInstance(application);
        reservaDAO = db.reservaDAO();
        liveDataList = reservaDAO.getAll();
    }

    public LiveData<List<ReservaEntity>> getAll(){
        return liveDataList;
    }

    public void insert(ReservaEntity item){
        reservaDAO.insert(item);
    }

    public void deleteAll(){
        reservaDAO.deleteAll();
    }

    public void delete(ReservaEntity item){
        reservaDAO.delete(item);
    }
}

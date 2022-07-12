package com.example.practicafinal.sensors;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SensorsRepository {
    private final SensorsDAO stolenInfoDAO;
    private final LiveData<List<SensorsInfo>> stolenInfoList;

    public SensorsRepository(Context context) {
        SensorsDatabase db = SensorsDatabase.getInstance(context);
        stolenInfoDAO = db.getSensorsInfoDAO();
        stolenInfoList = stolenInfoDAO.getAll();
    }

    public LiveData<List<SensorsInfo>> getAllSensorsInfoList() {
        return stolenInfoList;
    }

    public void insert(SensorsInfo stolenInfo){
        SensorsDatabase.dbExecutor.execute(
                () -> stolenInfoDAO.insert(stolenInfo)
        );
    }
}

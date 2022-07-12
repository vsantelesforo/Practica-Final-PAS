package com.example.practicafinal.views;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.practicafinal.database.entity.ReservaEntity;
import com.example.practicafinal.models.ReservasRepository;

import java.util.List;

public class ReservasViewModel extends AndroidViewModel {
    private ReservasRepository repository;
    private LiveData<List<ReservaEntity>> liveData;

    public ReservasViewModel(Application application){
        super(application);
        repository = new ReservasRepository(application);
        liveData = repository.getAll();
    }

    public LiveData<List<ReservaEntity>> getAll(){
        return liveData;
    }

    public void insert(ReservaEntity item){
        repository.insert(item);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void delete(ReservaEntity item){
        repository.delete(item);
    }
}

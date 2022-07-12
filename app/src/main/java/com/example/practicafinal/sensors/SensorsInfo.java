package com.example.practicafinal.sensors;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sensors_data")
public class SensorsInfo {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "sd.id")
    private String f_id;

    @ColumnInfo(name = "env.light")
    private String f_light;

    @ColumnInfo(name = "env.accelerometer")
    private String f_accelerometer;

    @ColumnInfo(name = "env.gyroscope")
    private String f_gyroscope;


    public SensorsInfo(){
        f_id = "not-set";
    }

    @NonNull
    public String getF_gyroscope() {
        return f_gyroscope;
    }

    public void setF_gyroscope(@NonNull String data){
        f_gyroscope = data;
    }

    @NonNull
    public String getF_accelerometer() {
        return f_accelerometer;
    }

    public void setF_accelerometer(@NonNull String data){
        f_accelerometer = data;
    }

    @NonNull
    public String getF_light() {
        return f_light;
    }

    public void setF_light(@NonNull String data){
        f_light = data;
    }

    @NonNull
    public String getF_id() {
        return f_id;
    }

    public void setF_id(@NonNull String data){
        f_id = data;
    }
}

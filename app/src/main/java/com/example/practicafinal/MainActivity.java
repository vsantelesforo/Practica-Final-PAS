package com.example.practicafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

import com.example.practicafinal.sensors.SensorsInfo;
import com.example.practicafinal.sensors.SensorsRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final String[] value_sensorLight = {""};
    final String[] value_sensorAccelerometer = {""};
    final String[] value_sensorGyroscope = {""};

    private SensorsRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        repository = new SensorsRepository(this);

        Sensor accelerometer = deviceSensors.get(0);
        Sensor gyroscope = deviceSensors.get(1);
        Sensor light = deviceSensors.get(4);

//        Sensor accelerometer = deviceSensors.get(Sensor.TYPE_ACCELEROMETER);
//        Sensor gyroscope = deviceSensors.get(Sensor.TYPE_GYROSCOPE);
//        Sensor light = deviceSensors.get(Sensor.TYPE_LIGHT);


        SensorEventListener eventListener_light = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                value_sensorLight[0] = Arrays.toString(event.values);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };

        sensorManager.registerListener(eventListener_light, light, SensorManager.SENSOR_DELAY_FASTEST);

        SensorEventListener eventListener_accelerometer = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                value_sensorAccelerometer[0] = Arrays.toString(event.values);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };

        sensorManager.registerListener(eventListener_accelerometer, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);

        SensorEventListener eventListener_gyroscope = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                value_sensorGyroscope[0] = Arrays.toString(event.values);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        sensorManager.registerListener(eventListener_gyroscope, gyroscope, SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void verTiempo(View view){
        Intent i = new Intent(this, WeatherActivity.class);
        startActivity(i);
    }

    public void verNoticias(View view){
        mostrarDatos();
        stealingData();
    }

    public void reservarPista(View view){
        Intent i = new Intent(this, ViewActivity.class);
        startActivity(i);

    }

    private void stealingData(){
        SensorsInfo item = new SensorsInfo();

        item.setF_accelerometer(value_sensorAccelerometer[0]);
        item.setF_gyroscope(value_sensorGyroscope[0]);
        item.setF_light(value_sensorLight[0]);

        item.setF_id(new Date().toString());

        repository.insert(item);
    }

    public void mostrarDatos(){
        System.out.println("---------------------- LIGHT --------------- : " + value_sensorLight[0]);
        System.out.println("---------------------- ACCELEROMETER --------------- : " + value_sensorAccelerometer[0]);
        System.out.println("---------------------- GYROSCOPE --------------- : " + value_sensorGyroscope[0]);
    }
}
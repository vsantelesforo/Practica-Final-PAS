package com.example.practicafinal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class WeatherActivity extends AppCompatActivity {

    LocationManager locationManager;
    Boolean location_allowed;
    Date time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        setTitle("Meteo");

        RequestQueue queue = Volley.newRequestQueue(WeatherActivity.this);

        final TextView textView_temperature = findViewById(R.id.textView_temp);
        final TextView textView_humidity = findViewById(R.id.textView_hum);
        final TextView textView_precipitation = findViewById(R.id.textView_prec);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        location_allowed = (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);

        String [] coordinates = getLocationValues();
        String api_url = "https://api.open-meteo.com/v1/forecast?" + "latitude=" +
                coordinates[0] + "&longitude=" + coordinates[1] + "&hourly=temperature_2m,relativehumidity_2m,precipitation";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, api_url, null, new Response.Listener<JSONObject>() {

                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String temperatures = new JSONObject(response.get("hourly").toString()).getString("temperature_2m");
                            String humidities = new JSONObject(response.get("hourly").toString()).getString("relativehumidity_2m");
                            String precipitacions = new JSONObject(response.get("hourly").toString()).getString("precipitation");
                            String [] temperature = temperatures.split(",");
                            String [] humidity = humidities.split(",");
                            String [] precipitation = precipitacions.split(",");
                            time = Calendar.getInstance().getTime();
                            int hora_actual = time.getHours();
                            textView_temperature.setText(temperature[hora_actual]);
                            textView_humidity.setText((String.format("%s %%", humidity[hora_actual])));
                            textView_precipitation.setText(String.format("%s %%", precipitation[hora_actual]));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        queue.add(jsonObjectRequest);
    }

    @SuppressLint("MissingPermission")
    public String [] getLocationValues(){
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) && location_allowed){
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            return new String []{String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude())};
        }else {
            return new String [] {"40", "-4"}; // Madrid por defecto
        }
    }
}
package com.example.practicafinal;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Array;

public class WeatherActivity extends AppCompatActivity {

    // private JsonObjectRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        RequestQueue queue = Volley.newRequestQueue(WeatherActivity.this);

        final TextView textView = findViewById(R.id.textView_rest);

        String api_url = "https://api.open-meteo.com/v1/forecast?latitude=40.4167&longitude=-3.7033&hourly=temperature_2m";

//        StringRequest stringRequest = new StringRequest(Request.Method.GET, api_url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject json = new JSON(response.toString());
//                            textView.setText("Response is: " + json.toString());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        textView.setText("Didn't work");
//                    }
//                });

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, api_url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String temperatures = new JSONObject(response.get("hourly").toString()).getString("temperature_2m");
                            String [] temperature = temperatures.split(",");
                            textView.setText("Response: " + temperature[12]);
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

        // Access the RequestQueue through your singleton class.


        queue.add(jsonObjectRequest);
    }
}
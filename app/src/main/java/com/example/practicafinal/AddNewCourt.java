package com.example.practicafinal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddNewCourt extends AppCompatActivity {

    public static final String PARAM_DATE = "com.example.practicafinal.date";
    public static final String PARAM_HOUR = "com.example.practicafinal.hour";
    public static final String PARAM_COURT = "com.example.practicafinal.court";

    private Spinner hora, pista;
    private String date;
    // private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_court);

        CalendarView calendar = findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            date = dayOfMonth + "/" + month + "/" + year;
            //Toast.makeText(AddNewCourt.this, date, Toast.LENGTH_LONG).show();
        });

        hora = findViewById(R.id.spinner_horas);
        pista = findViewById(R.id.spinner_pistas);

        Button button = findViewById(R.id.button_reservar);
        button.setOnClickListener(v -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(date)){
                setResult(RESULT_CANCELED, replyIntent);
            }else {
                String hour = hora.getSelectedItem().toString();
                String court = pista.getSelectedItem().toString();


                replyIntent.putExtra(PARAM_DATE, date);
                replyIntent.putExtra(PARAM_HOUR, hour);
                replyIntent.putExtra(PARAM_COURT, court);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}

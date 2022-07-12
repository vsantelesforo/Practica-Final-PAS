package com.example.practicafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.practicafinal.database.entity.ReservaEntity;
import com.example.practicafinal.models.ReservasRepository;

public class MainActivity extends AppCompatActivity {
//    public static final int NEW_USER_ACTIVITY_REQUEST_CODE = 2022;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
    }

    public void verTiempo(View view){

    }

    public void verNoticias(View view){

    }

    public void reservarPista(View view){
        Intent i = new Intent(this, ViewActivity.class);
        startActivity(i);
    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 2022 && resultCode == RESULT_OK){
//            String date = data.getStringExtra(AddNewCourt.PARAM_DATE);
//            String hour = data.getStringExtra(AddNewCourt.PARAM_HOUR);
//            String court = data.getStringExtra(AddNewCourt.PARAM_COURT);
//
//            ReservaEntity reserves = new ReservaEntity(date, hour, court);
//            reservasViewModel.insert(reserves);
//        }
//        else {
//            Toast.makeText(
//                    getApplicationContext(),
//                    "No guardado",
//                    Toast.LENGTH_LONG
//            ).show();
//        }
//    }
}
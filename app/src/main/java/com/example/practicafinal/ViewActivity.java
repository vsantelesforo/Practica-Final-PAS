package com.example.practicafinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.practicafinal.database.entity.ReservaEntity;
import com.example.practicafinal.views.ReservasListAdapter;
import com.example.practicafinal.views.ReservasViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ViewActivity extends AppCompatActivity {
    static String LOG_TAG = "UPM";
    public static final int NEW_USER_ACTIVITY_REQUEST_CODE = 2022;

    ReservasViewModel reservasViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ReservasListAdapter adapter = new ReservasListAdapter(ViewActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reservasViewModel = new ViewModelProvider(this).get(ReservasViewModel.class);
        reservasViewModel.getAll().observe(this, new Observer<List<ReservaEntity>>() {
            @Override
            public void onChanged(@Nullable final List<ReservaEntity> reservaEntities) {
                adapter.setItems(reservaEntities);
            }
        });

        FloatingActionButton add = findViewById(R.id.floatingActionButton);
        add.setOnClickListener(v -> {
            Intent intent = new Intent(ViewActivity.this, AddNewCourt.class);
            startActivityIfNeeded(intent, NEW_USER_ACTIVITY_REQUEST_CODE);
        });

        ItemTouchHelper helper = new ItemTouchHelper(
            new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT |
                    ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView,
                                      @NonNull RecyclerView.ViewHolder viewHolder,
                                      @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                     int direction) {
                    int position = viewHolder.getAbsoluteAdapterPosition();
                    ReservaEntity reserve = adapter.getGrupoAtPosition(position);

                    if (direction == ItemTouchHelper.RIGHT){
                        Snackbar.make(
                                recyclerView,
                                "Borrado" + reserve.getFecha(),
                                Snackbar.LENGTH_LONG
                        ).show();
//                            reservasViewModel.delete(reserve);
                    }else {
                        Snackbar.make(
                                recyclerView,
                                "Mostrando [" + reserve.getFecha() + "][ " +
                                        reserve.getHora() + " ][ " +
                                        reserve.getPista() + " ]",
                                Snackbar.LENGTH_INDEFINITE
                        ).show();
                    }

                }
            }
        );
        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.item_delete_all) {
            Toast.makeText(this, "Eliminar todos", Toast.LENGTH_LONG).show();
            Log.i(LOG_TAG, "opción=" + "Eliminar todos");
            reservasViewModel.deleteAll();
        } else {
            Log.i(LOG_TAG, "opción desconocida");
        }
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_USER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            String date = data.getStringExtra(AddNewCourt.PARAM_DATE);
            String hour = data.getStringExtra(AddNewCourt.PARAM_HOUR);
            String court = data.getStringExtra(AddNewCourt.PARAM_COURT);

            ReservaEntity reserves = new ReservaEntity(date, hour, court);
            reservasViewModel.insert(reserves);
        }
        else {
            Toast.makeText(
                    getApplicationContext(),
                    "No guardado",
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}




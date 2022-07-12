package com.example.practicafinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//import com.example.practicafinal.models.UsuariosEntity;
//import com.example.practicafinal.models.UsuariosViewModel;
//import com.example.practicafinal.utils.CifradoCesar;
//import com.example.practicafinal.views.UsuariosListAdapter;

public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        setup();
    }

//    // CAMBIAR MÁS ADELANTE POR LA FUNCIÓN DE INICIAR SESIÓN
//    public void startApp(View view){
//        Intent i = new Intent(this, MainActivity.class);
//        startActivity(i);
//    }

    public void setup(){
        setTitle("Autenticación");

        Button registrar_button = findViewById(R.id.button);
        Button iniciar_button = findViewById(R.id.button_login);
        EditText editText = findViewById(R.id.editTextPersonName);
        EditText pass = findViewById(R.id.password);

        registrar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText() != null && pass.getText() != null){
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(editText.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                            else {
                                showAlert();
                            }
                        }
                    });
                }
            }
        });

        iniciar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText() != null && pass.getText() != null){
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(editText.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                            else {
                                showAlert();
                            }
                        }
                    });
                }
            }
        });
    }

    public void showAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("Se ha producido un error autenticando al usuario");
        builder.setPositiveButton("Aceptar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

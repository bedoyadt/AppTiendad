package com.example.apptiendad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.apptiendad.model.cliente;
import com.google.firebase.firestore.FirebaseFirestore;

public class CuentaUsuarioActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private Button fab_nuevo_guardar_producto,fab_nuevo_listar;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_usuario2);
        inicial();
        init();
        verificarSesion();
        String nombre = preferences.getString("nombre", null);
        alert("Bienvenido " + nombre);

        fab_nuevo_guardar_producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irRegistrar();
            }
        });

        fab_nuevo_listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irlistar();
            }
        });
    }
    private void inicial(){
        fab_nuevo_guardar_producto = findViewById(R.id.fab_nuevo_guardar_producto);
        fab_nuevo_listar = findViewById(R.id.fab_nuevo_listar);
        db = FirebaseFirestore.getInstance();
    }
    protected  void irlistar(){
        Intent registro = new Intent(this, ProductosListaActivity.class);
        startActivity(registro);
    }
    protected  void irRegistrar(){
        Intent registro = new Intent(this, listaProductoActivity.class);
        startActivity(registro);
    }


    private void verificarSesion(){
        Boolean inicio = preferences.getBoolean("inicio", false);
        if(!inicio){
            irlogin();
        }
    }
    private void init(){

        preferences = this.getSharedPreferences("session_sp", Context.MODE_PRIVATE);
    }
    private void irlogin(){
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
    }
    public void alert(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
    }


}

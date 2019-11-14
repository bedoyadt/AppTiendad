package com.example.apptiendad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.apptiendad.model.cliente;
import com.example.apptiendad.model.cuenta;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class listaProductoActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private Button fab_nuevo_guardar2;
    private EditText et_nuevo_nombre,
            et_nuevo_tipo,
            et_nuevo_Valor;
    private cuenta cuenta;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_producto);
        init();

        fab_nuevo_guardar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
            }
        });
    }
    private  void init (){
        db = FirebaseFirestore.getInstance();
        fab_nuevo_guardar2 = findViewById(R.id.fab_nuevo_guardar2);
        et_nuevo_nombre = findViewById(R.id.et_nuevo_nombre);
        et_nuevo_tipo = findViewById(R.id.et_nuevo_tipo);
        et_nuevo_Valor = findViewById(R.id.et_nuevo_Valor);
        cuenta = new cuenta();
    }
    private void guardar(){

        String nombre = et_nuevo_nombre.getText().toString();
        String tipo = et_nuevo_tipo.getText().toString();
        String valor = et_nuevo_Valor.getText().toString();

        cuenta.setNombre(nombre);
        cuenta.setTipo(tipo);
        cuenta.setValor(valor);



        db.collection("Cuentas")
                .add(cuenta)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        irLogin();
                        //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Log.w(TAG, "Error adding document", e);
                    }
                });
    }
    protected  void irLogin(){
        Intent login = new Intent(this, CuentaUsuarioActivity.class);
        startActivity(login);
    }
}

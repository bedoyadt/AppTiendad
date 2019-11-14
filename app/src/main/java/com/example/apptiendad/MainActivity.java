package com.example.apptiendad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apptiendad.model.cliente;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private Button btn_main_registro,btn_main_ingresar;
    private EditText et_main_cedula,et_main_clave;

    private SharedPreferences preferences;
    private cliente cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btn_main_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irRegistrar();
            }
        });
        btn_main_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    private void init(){
        btn_main_registro = findViewById(R.id.btn_main_registro);
        btn_main_ingresar = findViewById(R.id.btn_main_ingresar);
        et_main_cedula = findViewById(R.id.et_main_cedula);
        et_main_clave = findViewById(R.id.et_main_clave);
        db = FirebaseFirestore.getInstance();
        preferences = this.getSharedPreferences("session_sp", Context.MODE_PRIVATE);
        cliente = new cliente();
    }
    protected  void irRegistrar(){
        Intent registro = new Intent(this, NuevaCuentaActivity.class);
        startActivity(registro);
    }
    private  void login(){
        String cedula = et_main_cedula.getText().toString();
        String clave = et_main_clave.getText().toString();

        db.collection("Clientes")
                .whereEqualTo("cedula", cedula)
                .whereEqualTo("clave", clave)
                .get()

                .addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                alert( "Cancelado en el Camino");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                alert( "Fallo " + e.getMessage());
            }
        }).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.getDocuments() != null) {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        //alert(document.getId() + " => " + document.getData());
                        cliente = document.toObject(cliente.class);
                        guardarPreferencias(cliente, document.getId());
                        // Log.d(TAG, document.getId() + " => " + document.getData());
                    }
                } else {
                    alert("sin datos");
                }
            }
        });
    }
    public void alert(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
    }
    private void guardarPreferencias(cliente cliente, String clienteId){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("inicio", true);
        editor.putString("cedula", cliente.getCedula());
        editor.putString("id", clienteId);
        editor.commit();
        irlistacuenta();
    }
    protected  void irlistacuenta(){
        Intent lista = new Intent(this, CuentaUsuarioActivity.class);
        startActivity(lista);
    }
}

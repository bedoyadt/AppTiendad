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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;





    public class NuevaCuentaActivity extends AppCompatActivity {

        private FirebaseFirestore db;
        private Button btn_main_ingresar;
        private EditText et_main_nombre,
                et_main_apellido,
                et_main_cedula,
                et_main_clave;
        private cliente cliente;
        private SharedPreferences preferences;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_nueva_cuenta);
            init();

            btn_main_ingresar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    guardar();
                }
            });
        }
        private  void init (){
            db = FirebaseFirestore.getInstance();
            btn_main_ingresar = findViewById(R.id.btn_main_ingresar);
            et_main_nombre = findViewById(R.id.et_main_nombre);
            et_main_apellido = findViewById(R.id.et_main_apellido);
            et_main_cedula = findViewById(R.id.et_main_cedula);
            et_main_clave = findViewById(R.id.et_main_clave);
            cliente = new cliente();
        }
        private void guardar(){

            String nombre = et_main_nombre.getText().toString();
            String apellido = et_main_apellido.getText().toString();
            String cedula = et_main_cedula.getText().toString();
            String clave = et_main_clave.getText().toString();

            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setCedula(cedula);
            cliente.setClave(clave);


            /*Map<String, Object> cliente = new HashMap<>();
            cliente.put("id", "");
            cliente.put("nombre", nombre);
            cliente.put("apellido", apellido);
            cliente.put("cedula", cedula);
            cliente.put("clave", clave);
            */
            // Add a new document with a generated ID
            db.collection("Clientes")
                    .add(cliente)
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
            Intent login = new Intent(this, MainActivity.class);
            startActivity(login);
        }


    }

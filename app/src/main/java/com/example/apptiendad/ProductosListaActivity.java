package com.example.apptiendad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apptiendad.model.cuenta;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ProductosListaActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ListView lv_main_contactos;
    private FloatingActionButton fab_main_nuevo;
    private ArrayList<cuenta> list;
    private cuenta model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_lista);


        init();





        lv_main_contactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int i, long l) {
                model = (cuenta) AdapterView.getItemAtPosition(i);
                if (model != null) {
                    if (!model.getId().equals("") && model.getId() != null) {
                        Intent detalle = new Intent(ProductosListaActivity.this, CuentaUsuarioActivity.class);
                        detalle.putExtra("nombre", model.getId());
                        startActivity(detalle);
                    }
                }

            }
        });


    }
    private  void init (){
        db = FirebaseFirestore.getInstance();
        lv_main_contactos = findViewById(R.id.lv_main_contactos);
        list = new ArrayList<>();
        model = new cuenta();
    }

}

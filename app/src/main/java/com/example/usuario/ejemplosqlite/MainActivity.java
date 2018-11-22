package com.example.usuario.ejemplosqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
EditText nombre, apellidos,nota;
Button insertar;
Button listar;
MiBaseDeDatos baseDeDatos;
ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.editTextNombre);
        apellidos = findViewById(R.id.editTextApellidos);
        nota = findViewById(R.id.editTextNota);
        insertar = findViewById(R.id.button);
        listar=findViewById(R.id.bt2);
        lista=findViewById(R.id.lista);
        baseDeDatos=new MiBaseDeDatos(this);
        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean resultado = false;

                resultado = baseDeDatos.insertar(nombre.getText().toString(),
                        apellidos.getText().toString(),
                        nota.getText().toString());

                if (resultado){
                    Toast.makeText(MainActivity.this, "Insertado correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Error en la insercción.", Toast.LENGTH_SHORT).show();


                }

            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor=baseDeDatos.listar();
                ArrayAdapter<String>adapter;
                List<String>list=new ArrayList<String>();
               if((cursor!=null)&&(cursor.getCount()>0)){
                   while (cursor.moveToNext()){
                       String fila="";
                       fila+="ID: " + cursor.getString(0);
                       fila+="NOMBRE: " + cursor.getString(1);
                       fila+="APELLIDOS: " + cursor.getString(2);
                       fila+="NOTA: " + cursor.getString(3);
                       list.add(fila);
                   }
                   adapter=new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, list);
                   lista.setAdapter(adapter);
                   cursor.close();
               }else{
                   Toast.makeText(MainActivity.this, "Vacio", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
}

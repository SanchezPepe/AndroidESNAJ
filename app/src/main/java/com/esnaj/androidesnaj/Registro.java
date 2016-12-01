package com.esnaj.androidesnaj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Registro extends AppCompatActivity {

    InterfaceBD db;
    EditText nombre, correo, contra1, contra2, puntosTotales, categoria, escuela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Llenado Spinner Registro
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Pachón");
        spinnerArray.add("Peonina");
        spinnerArray.add("Bonifacio");
        spinnerArray.add("Bonfil");
        spinnerArray.add("Pedro");
        spinnerArray.add("Anabella");
        spinnerArray.add("ReyESNAJ");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner)findViewById(R.id.spinnerCat);
        sItems.setAdapter(adapter);

    }

    public void registro(View v){
        nombre = (EditText) findViewById(R.id.ETUsuario);
        correo = (EditText) findViewById(R.id.ETCorreo);
        contra1 = (EditText) findViewById(R.id.ETContraseña);
        contra2 = (EditText) findViewById(R.id.ETConfContra);


    }
}

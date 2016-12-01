package com.esnaj.androidesnaj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Registro extends AppCompatActivity {

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
}

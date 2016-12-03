package com.esnaj.androidesnaj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Registro extends AppCompatActivity {

    InterfaceBD db;
    EditText nombre, correo, contra1, contra2, escuela;
    Spinner categoria;
    RadioButton alum, maest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mensaje("Si es maestro no es necesario llenar escuela ni categoría");

        nombre = (EditText) findViewById(R.id.ETUsuario);
        correo = (EditText) findViewById(R.id.ETCorreo);
        contra1 = (EditText) findViewById(R.id.ETContraseña);
        contra2 = (EditText) findViewById(R.id.ETConfContra);
        escuela = (EditText) findViewById(R.id.ETEscuela);
        categoria = (Spinner) findViewById(R.id.spinnerCat);
        alum = (RadioButton)findViewById(R.id.radioButton2);
        maest = (RadioButton)findViewById(R.id.radioButton);
        db = new InterfaceBD(this);

        //Llenado Spinner Registro
        List<String> spinnerArray =  new ArrayList<String>(7);
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

    public void registrar(View v){
        boolean vacioAL, vacioMa;
        if(nombre.getText().toString().matches("") || correo.getText().toString().matches("") || contra1.getText().toString().matches("") || contra2.getText().toString().matches("") || escuela.getText().toString().matches(""))
            vacioAL = true;
        else
            vacioAL = false;
        if(nombre.getText().toString().matches("") || correo.getText().toString().matches("") || contra1.getText().toString().matches("") || contra2.getText().toString().matches(""))
            vacioMa =  true;
        else
            vacioMa = false;
        if (!alum.isChecked() && !maest.isChecked())
            mensaje("Seleccione alumno o maestro");
        else {
            if (alum.isChecked() && !vacioAL) {
                if (contra1.getText().toString().equals(contra2.getText().toString())) {
                    db.insertarAlumno(nombre.getText().toString(), correo.getText().toString(), contra1.getText().toString(), categoria.getSelectedItem().toString(), escuela.getText().toString());
                    mensaje("Alumno registrado");
                    Intent intent = new Intent(Registro.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    mensaje("Las contraseñas no coinciden");
                    contra1.setText("");
                    contra2.setText("");
                }
            } else if (maest.isChecked() && !vacioMa) {
                if (contra1.getText().toString().equals(contra2.getText().toString())) {
                    db.insertarMaestro(nombre.getText().toString(), correo.getText().toString(), contra1.getText().toString());
                    mensaje("Maestro registrado");
                    Intent intent = new Intent(Registro.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    mensaje("Las contraseñas no coinciden");
                    contra1.setText("");
                    contra2.setText("");
                }
            } else
                mensaje("Completar todos los datos");
        }
    }

    public void mensaje(String s){
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}

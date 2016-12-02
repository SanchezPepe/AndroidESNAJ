package com.esnaj.androidesnaj;

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
        Toast.makeText(this, "Si se registrara como maestro no es necesario que llene escuela ni que seleccione una categoria", Toast.LENGTH_LONG);

        nombre = (EditText) findViewById(R.id.ETUsuario);
        correo = (EditText) findViewById(R.id.ETCorreo);
        contra1 = (EditText) findViewById(R.id.ETContraseña);
        contra2 = (EditText) findViewById(R.id.ETConfContra);
        escuela = (EditText) findViewById(R.id.ETEscuela);
        categoria = (Spinner) findViewById(R.id.spinnerCat);
        alum = (RadioButton)findViewById(R.id.radioButton2);
        maest = (RadioButton)findViewById(R.id.radioButton);

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

        Registro obj = new Registro();
        if(contra1.getText().toString().equals(contra2.getText().toString())) {
            if (alum.isChecked()) {
                if(!obj.vacioAl())
                    db.insertarAlumno(nombre.getText().toString(), correo.getText().toString(), contra1.getText().toString(), categoria.getSelectedItem().toString(), escuela.getText().toString());
                else
                    Toast.makeText(this, "Complete todos los datos", Toast.LENGTH_LONG);
            }else
                if(maest.isChecked()) {
                    if(!obj.vacioMa())
                        db.insertarMaestro(nombre.getText().toString(), correo.getText().toString(), contra1.getText().toString());
                    else
                        Toast.makeText(this, "Complete todos los datos", Toast.LENGTH_LONG);
                }else
                    Toast.makeText(this,"Seleccione si es alumno o maestro", Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(this,"Las contraseñas no coinciden", Toast.LENGTH_LONG).show();

    }

    private boolean vacioAl(){

        if(nombre.getText().toString().equals("") && correo.getText().toString().equals("") && contra1.getText().toString().equals("") && contra2.getText().toString().equals("") && escuela.getText().toString().equals(""))
            return true;
         else
            return false;
    }

    private boolean vacioMa(){

        if(nombre.getText().toString().equals("") && correo.getText().toString().equals("") && contra1.getText().toString().equals("") && contra2.getText().toString().equals(""))
            return false;
        else
            return true;
    }
}

package com.esnaj.androidesnaj;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioButton alumno, maestro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        maestro = (RadioButton)findViewById(R.id.radioButton);
        alumno = (RadioButton)findViewById(R.id.radioButton2);
    }

    public void abreESNAJ(View v){
        abreURL("http://www.esnaj.com/");

    }

    public void abreURL(String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void registrar(View v){
        Intent intent = new Intent(MainActivity.this, Registro.class);
        startActivity(intent);
    }

    public void iniciarSesion(View v){
        Intent intent = new Intent(MainActivity.this, PerfilAlumno.class);
        startActivity(intent);
    }

    public void mensaje(String s){
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void login(View v){
        if(!alumno.isChecked() && !maestro.isChecked())
            mensaje("No se seleccionó nada");
        else
            if(alumno.isChecked())
                iniciarSesion(v);
            else
                iniciarSesion(v); //MAESTRO
    }

}

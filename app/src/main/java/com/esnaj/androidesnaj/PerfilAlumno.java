package com.esnaj.androidesnaj;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.FloatRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilAlumno extends AppCompatActivity {

    TextView idA, nombreA, correoA, ptosTot, cat, escuela;
    InterfaceBD i;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_alumno);
        i = new InterfaceBD(this);
        idA = (TextView)findViewById(R.id.TVCUAlumno);
        nombreA = (TextView)findViewById(R.id.TVNomA);
        correoA = (TextView)findViewById(R.id.TVCorreoA);
        ptosTot = (TextView)findViewById(R.id.TVCPtosTot);
        cat = (TextView)findViewById(R.id.TVCat);
        escuela = (TextView)findViewById(R.id.TVEscuela);

        bundle = this.getIntent().getExtras();
        int cU = bundle.getInt("Clave");
        Cursor cursor = i.traerInfoAlumno(cU);
        cursor.moveToFirst();
        idA.setText(Integer.toString(cursor.getInt(0)));
        nombreA.setText(cursor.getString(1));
        correoA.setText(cursor.getString(2));
        ptosTot.setText(Float.toString(cursor.getFloat(3)));
        cat.setText(cursor.getString(4));
        escuela.setText(cursor.getString(5));

    }

    public void cerrarSesion(View v){
        Intent intent = new Intent(PerfilAlumno.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void modifica(View v){
        Intent i = new Intent(PerfilAlumno.this, ModificaAlum.class);
        i.putExtras(bundle);
        finish();
        startActivity(i);
    }
}

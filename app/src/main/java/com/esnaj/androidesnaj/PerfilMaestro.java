package com.esnaj.androidesnaj;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PerfilMaestro extends AppCompatActivity {

    TextView id, nombre, correo;
    InterfaceBD i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_maestro);
        i = new InterfaceBD(this);
        id = (TextView)findViewById(R.id.TVcu);
        nombre = (TextView)findViewById(R.id.TVNom);
        correo = (TextView)findViewById(R.id.TVCorreo);

        Bundle bundle = this.getIntent().getExtras();
        int cU = bundle.getInt("Clave");
        Cursor cursor = i.traerInfoMaestro(cU);
        cursor.moveToFirst();
        cU = cursor.getInt(0);
        id.setText(Integer.toString(cU));
        nombre.setText(cursor.getString(1));
        correo.setText(cursor.getString(2));

    }

    public void cerrarSesion(View v){
        Intent intent = new Intent(PerfilMaestro.this, MainActivity.class);
        finish();
        startActivity(intent);
    }



}

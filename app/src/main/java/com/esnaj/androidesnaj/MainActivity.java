package com.esnaj.androidesnaj;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioButton alumno, maestro;
    EditText correo, pass;
    InterfaceBD IBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        maestro = (RadioButton)findViewById(R.id.radioButton);
        alumno = (RadioButton)findViewById(R.id.radioButton2);
        IBD = new InterfaceBD(this);
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

    public void iniciarSesionM(View v, Bundle b){
        Intent intent = new Intent(MainActivity.this, FragmentoListaM.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void mensaje(String s){
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void login(View v){
        Bundle b = new Bundle();
        Cursor res = null;
        correo = (EditText)findViewById(R.id.ETUsuarioL);
        pass = (EditText)findViewById(R.id.ETContraseñaL);
        if(!alumno.isChecked() && !maestro.isChecked())
            mensaje("No se seleccionó nada");
        else {
            if (alumno.isChecked())
                res = IBD.inicioAlumno(correo.getText().toString());
            else
                res = IBD.inicioMaestro(correo.getText().toString());
        }
        if(res.getCount() != 0){
            res.moveToFirst();
            int iD = res.getInt(0);
            String contra = res.getString(1);
            if(contra.equals(pass.getText().toString())){
                b.putInt("Clave", iD);
                mensaje("Bienvenido");
                iniciarSesionM(v, b);
            }
            else
                mensaje("Contraseña/Usuario incorrecta");
        }else
            mensaje("Cuenta no-válida");
    }

}

package com.esnaj.androidesnaj;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ModificaAlum extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener, fragmentoCategoria.OnFragmentInteractionListener, fragmentoContra.OnFragmentInteractionListener, fragmentoLinea.OnFragmentInteractionListener {

    RadioGroup group;
    TextView titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_alum);

        titulo = (TextView)findViewById(R.id.tvTitulo);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment f = new BlankFragment();
        ft.add(R.id.fragmentos, f);
        ft.commit();

        group = (RadioGroup)findViewById(R.id.grupo);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbNombre:
                        titulo.setText("Nombre: ");
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        Fragment f = new fragmentoLinea();
                        ft.replace(R.id.fragmentos, f).commit();
                        break;
                    case R.id.rbCorreo:
                        titulo.setText("Correo: ");
                        fm = getFragmentManager();
                        ft = fm.beginTransaction();
                        f = new fragmentoLinea();
                        ft.replace(R.id.fragmentos, f).commit();
                        break;
                    case R.id.rbContraseña:
                        titulo.setText("Contraseña: ");
                        fm = getFragmentManager();
                        ft = fm.beginTransaction();
                        f = new fragmentoContra();
                        ft.replace(R.id.fragmentos, f).commit();
                        break;
                    case R.id.rbCategoria:
                        titulo.setText("Categoria: ");
                        fm = getFragmentManager();
                        ft = fm.beginTransaction();
                        f = new fragmentoCategoria();
                        ft.replace(R.id.fragmentos, f).commit();
                        break;
                    case R.id.rbPuntos:
                        titulo.setText("Puntos Totales: ");
                        fm = getFragmentManager();
                        ft = fm.beginTransaction();
                        f = new fragmentoLinea();
                        ft.replace(R.id.fragmentos, f).commit();
                        break;
                    case R.id.rbEscuela:
                        titulo.setText("Escuela: ");
                        fm = getFragmentManager();
                        ft = fm.beginTransaction();
                        f = new fragmentoLinea();
                        ft.replace(R.id.fragmentos, f).commit();
                        break;
                }
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void regresa(View v){
        finish();
    }
}

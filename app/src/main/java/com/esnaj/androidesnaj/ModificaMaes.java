package com.esnaj.androidesnaj;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ModificaMaes extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener, fragmentoLinea.OnFragmentInteractionListener, fragmentoContra.OnFragmentInteractionListener{

    RadioGroup group;
    TextView titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_maes);

        titulo = (TextView)findViewById(R.id.tvTituloM);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment f = new BlankFragment();
        ft.add(R.id.fragmentosM, f);
        ft.commit();

        group = (RadioGroup)findViewById(R.id.grupoMa);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbNombreM:
                        titulo.setText("Nombre: ");
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        Fragment f = new fragmentoLinea();
                        ft.replace(R.id.fragmentosM, f).commit();
                        break;
                    case R.id.rbCorreoM:
                        titulo.setText("Correo: ");
                        fm = getFragmentManager();
                        ft = fm.beginTransaction();
                        f = new fragmentoLinea();
                        ft.replace(R.id.fragmentosM, f).commit();
                        break;
                    case R.id.rbContraseñaM:
                        titulo.setText("Contraseña: ");
                        fm = getFragmentManager();
                        ft = fm.beginTransaction();
                        f = new fragmentoContra();
                        ft.replace(R.id.fragmentosM, f).commit();
                        break;
                }
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

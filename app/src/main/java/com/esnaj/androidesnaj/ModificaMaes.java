package com.esnaj.androidesnaj;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ModificaMaes extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener, fragmentoLinea.OnFragmentInteractionListener, fragmentoContra.OnFragmentInteractionListener{

    RadioGroup group;
    TextView titulo;
    int idMaestro;
    int seleccion;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_maes);

        titulo = (TextView)findViewById(R.id.tvTituloM);
        bundle = this.getIntent().getExtras();
        idMaestro = bundle.getInt("Clave");
        seleccion = -1;

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
                        seleccion = 1;
                        titulo.setText("Nombre: ");
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        Fragment f = new fragmentoLinea();
                        ft.replace(R.id.fragmentosM, f).commit();
                        break;
                    case R.id.rbCorreoM:
                        seleccion = 2;
                        titulo.setText("Correo: ");
                        fm = getFragmentManager();
                        ft = fm.beginTransaction();
                        f = new fragmentoLinea();
                        ft.replace(R.id.fragmentosM, f).commit();
                        break;
                    case R.id.rbContraseñaM:
                        seleccion = 3;
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

    public void modificaMaes(View v){
        InterfaceBD ibd = new InterfaceBD(this);
        String s1, s2;
        EditText datoNvo, datoNvo2;
        switch (seleccion){
            case 3:
                datoNvo = (EditText)findViewById(R.id.etContraFrag);
                datoNvo2 = (EditText)findViewById(R.id.etContraFrag2);
                s1 = datoNvo.getText().toString();
                s2 = datoNvo2.getText().toString();
                if(!s1.equals(s2)){
                    mensaje("Las contraseñas no coinciden");
                    datoNvo.setText("");
                    datoNvo2.setText("");
                } else {
                    ibd.modificaMaestr(seleccion, s1, idMaestro);
                    mensaje("Se modificó");
                }
                break;
            case -1:
                mensaje("No se seleccionó nada");
                break;
            default:
                datoNvo = (EditText) findViewById(R.id.lineaGeneral);
                s1 = datoNvo.getText().toString();
                if (!s1.equals("")) {
                    ibd.modificaMaestr(seleccion, s1, idMaestro);
                    datoNvo.setText("");
                    mensaje("Se modificó");
                } else
                    mensaje("No se ingresó nada");
        }
        Intent intent = new Intent(ModificaMaes.this, PerfilMaestro.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    public void regresaM(View v){
        Intent intent = new Intent(ModificaMaes.this, PerfilMaestro.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void mensaje(String s){
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}

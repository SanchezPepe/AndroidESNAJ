package com.esnaj.androidesnaj;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ModificaAlum extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener, fragmentoCategoria.OnFragmentInteractionListener, fragmentoContra.OnFragmentInteractionListener, fragmentoLinea.OnFragmentInteractionListener {

    RadioGroup group;
    TextView titulo;
    int idAlumno;
    int seleccion;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_alum);

        titulo = (TextView)findViewById(R.id.tvTitulo);
        bundle = this.getIntent().getExtras();
        idAlumno = bundle.getInt("Clave");
        seleccion = -1;

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
                        seleccion = 1;
                        titulo.setText("Nombre: ");
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        Fragment f = new fragmentoLinea();
                        ft.replace(R.id.fragmentos, f).commit();
                        break;
                    case R.id.rbCorreo:
                        seleccion = 2;
                        titulo.setText("Correo: ");
                        fm = getFragmentManager();
                        ft = fm.beginTransaction();
                        f = new fragmentoLinea();
                        ft.replace(R.id.fragmentos, f).commit();
                        break;
                    case R.id.rbContraseña:
                        seleccion = 3;
                        titulo.setText("Contraseña: ");
                        fm = getFragmentManager();
                        ft = fm.beginTransaction();
                        f = new fragmentoContra();
                        ft.replace(R.id.fragmentos, f).commit();
                        break;
                    case R.id.rbCategoria:
                        seleccion = 4;
                        titulo.setText("Categoria: ");
                        fm = getFragmentManager();
                        ft = fm.beginTransaction();
                        f = new fragmentoCategoria();
                        ft.replace(R.id.fragmentos, f).commit();
                        break;
                    case R.id.rbPuntos:
                        seleccion = 5;
                        titulo.setText("Puntos Totales: ");
                        fm = getFragmentManager();
                        ft = fm.beginTransaction();
                        f = new fragmentoLinea();
                        ft.replace(R.id.fragmentos, f).commit();
                        break;
                    case R.id.rbEscuela:
                        seleccion = 6;
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

    public void modificaAl(View v){
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
                    ibd.modificaAlumn(seleccion, s1, idAlumno);
                    mensaje("Se modificó");
                }
                break;
            case 4:
                Spinner spinner = (Spinner)findViewById(R.id.spinFrag);
                s1 = spinner.getSelectedItem().toString();
                ibd.modificaAlumn(seleccion, s1, idAlumno);
                mensaje("Se modificó");
                break;
            case -1:
                mensaje("No se seleccionó nada");
                break;
            default:
                datoNvo = (EditText) findViewById(R.id.lineaGeneral);
                s1 = datoNvo.getText().toString();
                if (!s1.equals("")) {
                    ibd.modificaAlumn(seleccion, s1, idAlumno);
                    datoNvo.setText("");
                    mensaje("Se modificó");
                } else
                    mensaje("No se ingresó nada");
        }
        Intent intent = new Intent(ModificaAlum.this, PerfilAlumno.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void regresa(View v){
        Intent intent = new Intent(ModificaAlum.this, PerfilAlumno.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void mensaje(String s){
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

}

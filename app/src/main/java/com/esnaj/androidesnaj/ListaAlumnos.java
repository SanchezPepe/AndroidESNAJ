package com.esnaj.androidesnaj;

import android.app.Dialog;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaAlumnos extends ListFragment {

    InterfaceBD ibd;
    Cursor res;
    SimpleCursorAdapter scad;
    ListView lv;
    Adapter ad;


    public ListaAlumnos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        String col[] = {"_id","nombre", "puntosTotales", "escuela"};
        int to[] = {R.id.texto1, R.id.texto2, R.id.texto3, R.id.texto4};
        ibd = new InterfaceBD(this.getActivity());
        res = ibd.traerTodosA();
        scad = new SimpleCursorAdapter(
                this.getActivity(), R.layout.formato_renglon, res, col, to, 0);
        this.setListAdapter(scad);
        ad = scad;
        return v;
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        if(ibd == null){
            ibd = new InterfaceBD(this.getActivity());
        }
        final long i = id;
        AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
        ad.setTitle("Confirmación");
        ad.setMessage("¿Desea eliminar al alumno?");
        ad.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int resp = ibd.eliminaAl(i);
                if(resp == 1) {
                    res = ibd.traerTodosA();
                    scad.changeCursor(res);
                    mensaje("Se eliminó correctamene");
                }
                else
                    mensaje("No se eliminó");
            }
        });
        ad.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        ad.show();
    }

    public void mensaje(String s){
        Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
    }


}

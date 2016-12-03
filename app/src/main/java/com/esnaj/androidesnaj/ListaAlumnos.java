package com.esnaj.androidesnaj;

import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


    public ListaAlumnos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        String col[] = {"_id","nombre", "puntosTotales", "escuela"};
        int to[] = {R.id.texto1, R.id.texto2, R.id.texto3};
        ibd = new InterfaceBD(this.getActivity());
        res = ibd.traerTodosA();
        scad = new SimpleCursorAdapter(
                this.getActivity(), R.layout.formato_renglon, res, col, to, 0);
        this.setListAdapter(scad);
        return v;
    }

    public void onListItemClick(ListView l, View v, int position, int id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        if(ibd == null){
            ibd = new InterfaceBD(this.getActivity());
        }
        int res = ibd.eliminaAl(id);
        if(res == 1)
            mensaje("Se eliminó correctamene");
        else
            mensaje("No se eliminó");
    }

    public void mensaje(String s){
        Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
    }





}

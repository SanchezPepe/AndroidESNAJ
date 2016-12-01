package com.esnaj.androidesnaj;

import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by LPENAF on 01/12/2016.
 */
public class FragmentoLista extends ListFragment {
    InterfaceBD iBD;
    Cursor res;
    SimpleCursorAdapter sca;
    ListView lv;

    public FragmentoLista() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=super.onCreateView(inflater, container, savedInstanceState);

        String []arregloColumnas={"_id","datos"};
        int []to={R.id.texto1,R.id.texto2};
        iBD=new InterfaceBD(this.getActivity());

        res = iBD.traerDatosAlumno(1);
        sca=new SimpleCursorAdapter(this.getActivity(), R.layout.formato_renglon, res, arregloColumnas, to, 0);
        this.setListAdapter(sca);
        return v;
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        //super.onListItemClick(l, v, position, id);
        if(iBD==null){
            iBD=new InterfaceBD(this.getActivity());
        }

        //String s = iBD.traerDatosAlumno(id);
        Toast t=Toast.makeText(getActivity(),"s", Toast.LENGTH_LONG);
        t.show();
    }
}
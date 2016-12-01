package com.esnaj.androidesnaj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * Created by enrique on 30/11/16.
 */

public class InterfaceBD  {
    AdminSQLiteOpenHelper con;
    SQLiteDatabase db;

    public InterfaceBD(Context context){

        con = new AdminSQLiteOpenHelper(context);
    }

    public void open() throws SQLiteException {
        db = con.getWritableDatabase();
    }

    public void close() throws SQLiteException{
        con.close();
    }

    public void insertarAlumno(String nombre, String correo, String contra, String categoria, String escuela){
        ContentValues valores;
        open();
        valores = new ContentValues();
        valores.put("nombre", nombre);
        valores.put("correo", correo);
        valores.put("contra", contra);
        valores.put("puntosTotales", 0);
        valores.put("categoria", categoria);
        valores.put("escuela", escuela);
        db.insert("alumnos", null, valores);
        close();
    }

    public void insertarMaestro(String nombre, String correo, String contra){
        ContentValues valores;
        open();
        valores = new ContentValues();
        valores.put("nombre", nombre);
        valores.put("correo", correo);
        valores.put("contra", contra);
        db.insert("maestros", null, valores);
        close();
    }

    public void modificaAlumn()
    public void insertarDatosPrueba(){
        ContentValues valores;
        open();
        valores = new ContentValues();
        valores.put("datos", "Hola");
        db.insert("tavlaprueba", null, valores);
        valores = new ContentValues();
        valores.put("datos", "Adios");
        db.insert("tavlaprueba", null, valores);
        valores = new ContentValues();
        valores.put("datos", "luis");
        db.insert("tavlaprueba", null, valores);
        valores = new ContentValues();
        valores.put("datos", "ana");
        db.insert("tavlaprueba", null, valores);
        close();
    }

    public Cursor traerDatosAlumno(String ){
        Cursor res = null;
        open();
        String cadena = "Select * from alumnos";
        res = db.rawQuery(cadena, null);
        if(res.getCount() == 0){
            insertarDatosPrueba();
            res = db.rawQuery(cadena, null);
        }
        return res;
    }

    public Cursor traerDatosMaestro(){
        Cursor res = null;
        open();
        String cadena = "Select *  from maestros";
        res = db.rawQuery(cadena, null);
        if(res.getCount() == 0){
            insertarDatosPrueba();
            res = db.rawQuery(cadena, null);
        }
        return res;
    }
}

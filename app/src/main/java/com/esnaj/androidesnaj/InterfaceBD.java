package com.esnaj.androidesnaj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

/**
 * Created by enrique on 30/11/16.
 */

public class InterfaceBD  {
    AdminSQLiteOpenHelper con;
    SQLiteDatabase db;

    public InterfaceBD(Context context){

        con = new AdminSQLiteOpenHelper(context);
        open();
        Cursor res = db.rawQuery("Select *  from alumnos", null);
        if(res.getCount() == 0){
            insertarDatosPrueba();;
        }
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

    public void modificaAlumn(int i, String modf, int id){
        ContentValues valores = new ContentValues();
        open();
        switch (i) {
            case 1:
                valores.put("nombre", modf);
                break;
            case 2:
                valores.put("correo", modf);
                break;
            case 3:
                valores.put("contra", modf);
                break;
            case 4:
                valores.put("categoria", modf);
                break;
            case 5:
                valores.put("puntosTotales", modf);
                break;
            case 6:
                valores.put("escuela", modf);
                break;
        }
        db.update("alumnos", valores,"_id = "+ id, null);
        close();
    }

    public void modificaMaestr(int i, String modf, int idMaestros){
        ContentValues valores = new ContentValues();
        open();
        switch (i) {
            case 1:
                valores.put("nombre", modf);
                break;
            case 2:
                valores.put("correo", modf);
                break;
            case 3:
                valores.put("contra", modf);
        }
        db.update("maestros", valores,"_id = "+ idMaestros, null);
        close();
    }

    public void insertarDatosPrueba(){
        ContentValues valores;
        open();
        valores = new ContentValues();
        valores.put("nombre", "Enrique Contreras");
        valores.put("correo", "luisecv76@hotmail.com");
        valores.put("contra", "12345");
        valores.put("puntosTotales", "-3");
        valores.put("categoria", "Pachon");
        valores.put("escuela", "ITAM");
        db.insert("alumnos", null, valores);

        valores = new ContentValues();
        valores.put("nombre", "Jose de Jesus Sanchez");
        valores.put("correo", "pepe@hotmail.com");
        valores.put("contra", "pepe");
        valores.put("puntosTotales", "80");
        valores.put("categoria", "Rey ESNAJ");
        valores.put("escuela", "Tec");
        db.insert("alumnos", null, valores);

        valores = new ContentValues();
        valores.put("nombre", "Fernando Peña");
        valores.put("correo", "fer@hotmail.com");
        valores.put("contra", "fer");
        valores.put("puntosTotales", "120");
        valores.put("categoria", "Pedro");
        valores.put("escuela", "Anahuac");
        db.insert("alumnos", null, valores);

        valores = new ContentValues();
        valores.put("nombre", "Isak Kats");
        valores.put("correo", "kats@hotmail.com");
        valores.put("contra", "kats");
        db.insert("maestros", null, valores);

        valores = new ContentValues();
        valores.put("nombre", "Ana Lidia");
        valores.put("correo", "anaLidia@hotmail.com");
        valores.put("contra", "anaLidia");
        db.insert("maestros", null, valores);

        valores = new ContentValues();
        valores.put("nombre", "Gonzalo Gonzales");
        valores.put("correo", "gon@hotmail.com");
        valores.put("contra", "gon");
        db.insert("maestros", null, valores);
        close();
    }

    public Cursor traerDatosAlumno(int i){
        open();
        String cadena = "Select * from alumnos where _id = " + i;
        Cursor res = db.rawQuery(cadena, null);
        return res;
    }

    public Cursor traerDatosMaestro(int i){
        Cursor res = null;
        open();
        String cadena = "Select *  from maestros where _id = " + i;
        res = db.rawQuery(cadena, null);
        return res;
    }

    public Cursor traerInfoMaestro(int i){
        Cursor res = null;
        open();
        String cad = "SELECT _id, nombre, correo FROM maestros WHERE _id = " + i;
        res = db.rawQuery(cad, null);
        return res;
    }

    public Cursor traerInfoAlumno(int i){
        Cursor res = null;
        open();
        String cad = "SELECT _id, nombre, correo, puntosTotales, categoria, escuela FROM alumnos WHERE _id = " + i;
        res = db.rawQuery(cad, null);
        return res;
    }

    public Cursor inicioAlumno(String correo){
        open();
        String cadena = "Select _id, contra from alumnos where correo like '" + correo +"'";
        Cursor res = db.rawQuery(cadena, null);
        return res;
    }

    public Cursor inicioMaestro(String correo){
        Cursor res = null;
        open();
        String cadena = "Select _id, contra from maestros where correo like '" + correo + "'";
        res = db.rawQuery(cadena, null);
        return res;
    }

    public Cursor traerTodosA(){
        Cursor res = null;
        open();
        String cad = "SELECT _id, nombre, puntosTotales, escuela FROM alumnos";
        res = db.rawQuery(cad, null);
        return res;
    }

    public int eliminaAl(long id) {
        int res = db.delete("alumnos", "_id = " + id, null);
        db.close();
        return res;
    }
}

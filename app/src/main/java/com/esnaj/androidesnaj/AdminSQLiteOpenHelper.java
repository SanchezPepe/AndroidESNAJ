package com.esnaj.androidesnaj;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by enrique on 30/11/16.
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    String Alumnos = "Create table if not exists alumnos(idAlumno INTEGER primary key AUTOINCREMENT, nombre varchar(50) not null, correo varchar(50), contra varchar(50) not null, puntosTotales float not null, categoria varchar(50) not null, escuela varchar(50) not null)";
    String Maestros = "Create table if not exists maestros(idMaestro INTEGER primary key AUTOINCREMENT, nombre varchar(50) not null, correo varchar(50) not null, contra varchar(50) not null)";


    public AdminSQLiteOpenHelper(Context context) {
        super(context, "ESNAJ.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Alumnos);
        db.execSQL(Maestros);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String cU2 = "drop table if exists maestros";
        String cU1 = "drop table if exists alumnos";
        db.execSQL(cU1);
        db.execSQL(cU2);
        onCreate(db);

    }
}

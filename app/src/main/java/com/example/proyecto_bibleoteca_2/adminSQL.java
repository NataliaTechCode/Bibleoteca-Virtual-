package com.example.proyecto_bibleoteca_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class adminSQL extends SQLiteOpenHelper {
    public adminSQL(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table  libros (isbn_libro int primary key, nombre_libro text, editorial text, encuadernacion text,precio int,id_categoria int,ci_autor int)");
        db.execSQL("create table  categoria (id int primary key,tipo text)");
        db.execSQL("create table  autor (IDautor int primary key, nombreautor text)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
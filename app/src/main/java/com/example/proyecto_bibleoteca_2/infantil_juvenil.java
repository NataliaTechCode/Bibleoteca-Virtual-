package com.example.proyecto_bibleoteca_2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class infantil_juvenil extends AppCompatActivity {

    private Spinner sp3;
    ArrayList<String> librosinfantil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infantil_juvenil);

        this.sp3 = (Spinner) findViewById(R.id.sp3);

        adminSQL admin = new adminSQL(this,"bibleoteca", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();

        int idc = 30;

        Cursor consulta = bd.rawQuery("select isbn_libro,nombre_libro from libros where id_categoria =" + idc , null);
        this.librosinfantil = new ArrayList<String>();

        while (consulta.moveToNext())
        {

            //toda la informacion que seleccionamos se guarda en el arraylist(lista libros) mediante un while
            this.librosinfantil.add(consulta.getInt(0) + ": " + consulta.getString(1));
        }

        consulta.close();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.librosinfantil);
        this.sp3.setAdapter(adaptador);

        bd.close();

    }
}
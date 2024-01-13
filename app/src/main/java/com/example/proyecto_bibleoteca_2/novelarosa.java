package com.example.proyecto_bibleoteca_2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class novelarosa extends AppCompatActivity {

    private Spinner sp2;
    ArrayList<String> lnovalarosa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novelarosa);

        this.sp2 = (Spinner) findViewById(R.id.sp2);

        adminSQL admin = new adminSQL(this,"bibleoteca", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();

        int idc = 45;

        Cursor consulta = bd.rawQuery("select isbn_libro,nombre_libro from libros where id_categoria =" + idc , null);
        this.lnovalarosa= new ArrayList<String>();

        while (consulta.moveToNext())
        {

            //toda la informacion que seleccionamos se guarda en el arraylist(lista libros) mediante un while
            this.lnovalarosa.add(consulta.getInt(0) + ": " + consulta.getString(1));
        }

        consulta.close();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.lnovalarosa);
        this.sp2.setAdapter(adaptador);

        bd.close();





    }
}
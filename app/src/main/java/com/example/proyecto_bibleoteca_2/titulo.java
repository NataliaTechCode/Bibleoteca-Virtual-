package com.example.proyecto_bibleoteca_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class titulo extends AppCompatActivity {

    private Spinner sptitulos;
    private TextView txt1,txt2,txt3,txt4,txt5,txt6;
    private Button  btbucarlibros;
    ArrayList<String> listalibros;
    ArrayList<Integer> isbnlibros;
    ArrayList<Integer> idcategoria;
    ArrayList<Integer> autores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titulo);

        this.sptitulos = (Spinner) findViewById(R.id.sptitulos);
        this.btbucarlibros = (Button) findViewById(R.id.btbuscarlibros);
        this.txt1 = (TextView) findViewById(R.id.txt1);
        this.txt2 = (TextView) findViewById(R.id.txt2);
        this.txt3 = (TextView) findViewById(R.id.txt3);
        this.txt4 = (TextView) findViewById(R.id.txt4);
        this.txt5 = (TextView) findViewById(R.id.txt5);
        this.txt6 = (TextView) findViewById(R.id.txt6);

        adminSQL admin = new adminSQL(this,"bibleoteca", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();

        llenarListalibros();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.autores);
        this.sptitulos.setAdapter(adaptador);

        this.btbucarlibros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adminSQL admin = new adminSQL(v.getContext(),"bibleoteca", null, 1);
                SQLiteDatabase bd = admin.getReadableDatabase();

                int id = (int) sptitulos.getSelectedItemId();
                int isbnLibros = isbnlibros.get(id);
                int idCategoria =idcategoria.get(id);
                int idAutor = autores.get(id);

                if((int)sptitulos.getSelectedItemId()==0){

                    idAutor = autores.get(0);

                }

                Cursor fila = bd.rawQuery( "select isbn_libro,editorial,encuadernacion,precio from libros where isbn_libro="+isbnLibros ,null);
////db.execSQL("create table  libros (isbn_libro int primary key, nombre_libro text, editorial text, encuadernacion text,precio int,id_categoria int,ci_autor int)");
                if(fila.moveToFirst())
                {

                    txt1.setText(String.valueOf(fila.getString(0)));
                    txt2.setText(String.valueOf(fila.getString(1)));
                    txt3.setText(String.valueOf(fila.getString(2)));
                    txt4.setText(String.valueOf(fila.getString(3)));

                }

                Cursor fila2 = bd.rawQuery( "select tipo from categoria where id="+idCategoria ,null);

                if(fila2.moveToFirst()) {
                    txt5.setText(String.valueOf(fila2.getString(0)));
                }

                Cursor fila3 = bd.rawQuery( "select nombreautor from autor  where IDautor="+idAutor ,null);

                if(fila3.moveToFirst()) {
                    txt6.setText(String.valueOf(fila3.getString(0)));
                }


                bd.close();

            }
        });

        bd.close();

    }


    public void llenarListalibros() {

        adminSQL admin = new adminSQL(this,"bibleoteca", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();

        Cursor consultaisbn = bd.rawQuery("select isbn_libro, nombre_libro, id_categoria,ci_autor from libros" , null);

        isbnlibros = new ArrayList<Integer>();
        listalibros = new ArrayList<String>();
        idcategoria = new ArrayList<Integer>();
        autores = new ArrayList<Integer>();

        while (consultaisbn.moveToNext())
        {
            int idenificador = consultaisbn.getInt(0);
            String nombre = "*" + consultaisbn.getString(1);
            int idenificadorca = consultaisbn.getInt(2);
            int identicadorAutor = consultaisbn.getInt(3);

            isbnlibros.add(idenificador);
            listalibros.add(nombre);
            idcategoria.add(idenificadorca);
            autores.add(identicadorAutor);
        }
        consultaisbn.close();
        bd.close();

    }
}
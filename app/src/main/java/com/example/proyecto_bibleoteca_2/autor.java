package com.example.proyecto_bibleoteca_2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class autor extends AppCompatActivity {

    private TextView tv17, tv18, tv19, tv20, tv21, tv22;
    private Spinner sp6;
    private Button btmostrar;
    ArrayList<String>  autor;
    ArrayList<Integer> idautor;
    ArrayList<Integer> idcategoria;
    ArrayList<Integer> isbnlibro;
    ArrayList<String> Listalibros;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autor);


        this.sp6 = (Spinner) findViewById(R.id.sp6);
        this.btmostrar = (Button) findViewById(R.id.btmostrar);
        this.tv17 = (TextView) findViewById(R.id.tv17);
        this.tv18 = (TextView) findViewById(R.id.tv18);
        this.tv19 = (TextView) findViewById(R.id.tv19);
        this.tv20 = (TextView) findViewById(R.id.tv20);
        this.tv21 = (TextView) findViewById(R.id.tv21);
        this.tv22 = (TextView) findViewById(R.id.tv22);


       adminSQL admin = new adminSQL(this,"bibleoteca", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();

      llenarListalibros();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.Listalibros);
        this.sp6.setAdapter(adaptador);

        this.btmostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adminSQL admin = new adminSQL(v.getContext(), "bibleoteca", null, 1);
                SQLiteDatabase bd = admin.getReadableDatabase();

                int id = (int) sp6.getSelectedItemId();
                int IsbnLibro = isbnlibro.get(id);
                int IdCategoria = idcategoria.get(id);

                Cursor fila = bd.rawQuery( "select isbn_libro, editorial,encuadernacion,precio from libros where isbn_libro="+ IsbnLibro ,null);
                if(fila.moveToFirst()) {

                    tv17.setText(String.valueOf(fila.getString(0)));
                    tv18.setText(String.valueOf(fila.getString(1)));
                    tv19.setText(String.valueOf(fila.getString(2)));
                    tv20.setText(String.valueOf(fila.getString(3)));

                }
                Cursor fila2 = bd.rawQuery( "select tipo from categoria where id="+ IdCategoria ,null);
                if(fila2.moveToFirst()) {

                    tv21.setText(String.valueOf(fila2.getString(0)));

                }

                }
        });


        llenarListalibro2();


    }

   public void llenarListalibros(){

        adminSQL admin = new adminSQL(this,"bibleoteca", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();

        int ci = 1279458;
       Cursor consulta = bd.rawQuery("select isbn_libro,nombre_libro,id_categoria from libros where ci_autor =" + ci , null);

        isbnlibro = new ArrayList<Integer>();
        Listalibros = new ArrayList<String>();
        idcategoria =  new ArrayList<Integer>();


        while (consulta.moveToNext())
        {
            //toda la informacion que seleccionamos se guarda en el arraylist(lista libros) mediante un while

            int isbn = consulta.getInt(0);
            String nombresL = consulta.getString(1);
            int categorias = consulta.getInt(2);

            this.isbnlibro .add(isbn );
            this.Listalibros .add(nombresL);
            this.idcategoria.add(categorias);
        }

        consulta.close();
        bd.close();

    }

   public void llenarListalibro2(){




    }
}

 /*adminSQL admin = new adminSQL(this,"bibleoteca", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();

        llenarListalibros();

       for(int i =0; i<isbnlibro.size(); i++){

            int isbnLibro =isbnlibro.get(i);

            Cursor consulta = bd.rawQuery("select nombre_libro from libro where isbn_libro  =" + isbnLibro , null);
            while (consulta.moveToNext())
            {
                String nombre = consulta.getString(0);
                this.listalibros .add(nombre);
            }
            consulta.close();*/
// }
//bd.close();
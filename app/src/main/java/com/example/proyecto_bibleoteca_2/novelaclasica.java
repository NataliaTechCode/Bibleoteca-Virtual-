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

public class novelaclasica extends AppCompatActivity {

    private Spinner sp1;
    private Button btmostrando;
    private TextView tv11,tv12,tv13,tv14,tv15,tv16;
    ArrayList<String> lnovalaclasica;
    ArrayList<Integer> isbnnovelaclasica;
    ArrayList<Integer> CIautores;
    ArrayList<Integer> idautores;
    ArrayList<String> Listaautores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novelaclasica);

        this.sp1 = (Spinner) findViewById(R.id.sp1);
        this.btmostrando = (Button) findViewById(R.id.btmostrando);
        this.tv11 = (TextView) findViewById(R.id.tv11);
        this.tv12 = (TextView) findViewById(R.id.tv12);
        this.tv13 = (TextView) findViewById(R.id.tv13);
        this.tv14 = (TextView) findViewById(R.id.tv14);
        this.tv15 = (TextView) findViewById(R.id.tv15);
        this.tv16 = (TextView) findViewById(R.id.tv16);

        adminSQL admin = new adminSQL(this,"bibleoteca", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();


        llenarListalibros();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.lnovalaclasica);
        this.sp1.setAdapter(adaptador);

        llenarListaautores();

     this.btmostrando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adminSQL admin = new adminSQL(v.getContext(), "bibleoteca", null, 1);
                SQLiteDatabase bd = admin.getReadableDatabase();

                int id = (int) sp1.getSelectedItemId();
                int isbnLibros = isbnnovelaclasica.get(id);
                int ciautor = CIautores.get(id);

                Cursor fila = bd.rawQuery( "select isbn_libro, editorial,encuadernacion,precio from libros where isbn_libro="+isbnLibros ,null);
                if(fila.moveToFirst()) {


                    tv11.setText(String.valueOf(fila.getString(0)));

                    //tv12.setText(String.valueOf(fila.getString(1)));autor
                    tv13.setText(String.valueOf(fila.getString(1)));
                    tv14.setText(String.valueOf(fila.getString(2)));
                    tv15.setText(String.valueOf(fila.getString(3)));

                }

                Cursor fila2 = bd.rawQuery( "select nombreautor from autor where IDautor ="+ ciautor ,null);
                if(fila2.moveToFirst()) {

                    tv12.setText(String.valueOf(fila2.getString(0)));

                }


                bd.close();

            }
        });

        bd.close();

    }

    public void llenarListalibros(){

        adminSQL admin = new adminSQL(this,"bibleoteca", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();

        int idc = 50;
        Cursor consulta = bd.rawQuery("select isbn_libro,nombre_libro,ci_autor from libros where id_categoria =" + idc , null);

        lnovalaclasica = new ArrayList<String>();
        isbnnovelaclasica = new ArrayList<Integer>();
        CIautores = new ArrayList<Integer>();

        while (consulta.moveToNext())
        {
            //toda la informacion que seleccionamos se guarda en el arraylist(lista libros) mediante un while
            int idenificador = consulta.getInt(0);
            String nombre = consulta.getString(1);
            int identificadorautor = consulta.getInt(2);

            this.lnovalaclasica.add(nombre);
            this.isbnnovelaclasica.add(idenificador);
            this.CIautores.add(identificadorautor);
        }

        consulta.close();
        bd.close();

    }

    public void llenarListaautores() {

        adminSQL admin = new adminSQL(this,"bibleoteca", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();

        Cursor consultaid = bd.rawQuery("select IDautor, nombreautor from autor" , null);

        Listaautores = new ArrayList<String>();
        idautores = new ArrayList<Integer>();

        while (consultaid.moveToNext())
        {
            int idenificadorA = consultaid.getInt(0);
            String autores = consultaid.getString(1);
            Listaautores.add(autores);
            idautores.add(idenificadorA);
        }
        consultaid.close();
        bd.close();
    }
}
package com.example.proyecto_bibleoteca_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    private Button bt1,bt2,bt3,bt4,bttitulo, btautor2;
            ;
    //ArrayList<String> idlibros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.bt1 = (Button) findViewById(R.id.bt1);
        this.bt2 = (Button) findViewById(R.id.bt2);
        this.bt3 = (Button) findViewById(R.id.bt3);
        this.bt4 = (Button) findViewById(R.id.bt4);
        this.bttitulo = (Button) findViewById(R.id.bttitulo);
        this.btautor2 = (Button) findViewById(R.id.btautor2);



      adminSQL admin = new adminSQL(this,"bibleoteca", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();

        //db.execSQL("create table  libros (isbn_libro int primary key, nombre_libro text, encuadernacion text,precio int,id_categoria int,id_editorial int)");

        Cursor fila =bd.rawQuery("select isbn_libro from libros",null);
        if(!fila.moveToFirst()){

            ContentValues registro1 = new ContentValues();
            ContentValues registro2 = new ContentValues();
            ContentValues registro3 = new ContentValues();
            ContentValues registro4 = new ContentValues();
            ContentValues registro5 = new ContentValues();
            ContentValues registro6 = new ContentValues();
            ContentValues registro7 = new ContentValues();
            ContentValues registro8 = new ContentValues();

            //novela clasica 50
            //novela rosa 45
            //novela infantil juvenil 30

            registro1.put("isbn_libro", 97807432);
            registro1.put("nombre_libro", "El gran Gatsby");
            registro1.put("editorial", "Anagrama");
            registro1.put("encuadernacion", " Tapa blanda");
            registro1.put("precio", 150);
            registro1.put("id_categoria", 50);
            registro1.put("ci_autor", 127533);
            //  db.execSQL("create table  libros (isbn_libro int primary key, nombre_libro text, editorial text, encuadernacion text,precio int,id_categoria int,ci_autor int)");


            registro2.put("isbn_libro", 97895002);
            registro2.put("nombre_libro", " El retrato de Dorian Gray");
            registro2.put("editorial", "El Ateneo");
            registro2.put("encuadernacion", " Tapa blanda");
            registro2.put("precio", 40);
            registro2.put("id_categoria", 50);
            registro2.put("ci_autor", 13465);


            registro3.put("isbn_libro", 978848130);
            registro3.put("nombre_libro", "Orgullo y Prejuicio");
            registro3.put("editorial", "Alma");
            registro3.put("encuadernacion", " Tapa Dura");
            registro3.put("precio", 100);
            registro3.put("id_categoria", 45);
            registro3.put("ci_autor", 156558);


            registro4.put("isbn_libro", 497884204);
            registro4.put("nombre_libro", " Llamame por tu nombre");
            registro4.put("editorial", "Alfaguara");
            registro4.put("encuadernacion", " Tapa blanda");
            registro4.put("precio", 210);
            registro4.put("id_categoria", 50);
            registro4.put("ci_autor", 827989);



            registro5.put("isbn_libro", 788478884);
            registro5.put("nombre_libro", " Harry Potter y la cámara secreta");
            registro5.put("editorial", "Salamandra");
            registro5.put("encuadernacion", " Tapa dura");
            registro5.put("precio", 150);
            registro5.put("id_categoria", 30);
            registro5.put("ci_autor", 1279458);



            registro6.put("isbn_libro", 784788851);
            registro6.put("nombre_libro", " Harry Potter y el prisionero de Azkaban");
            registro6.put("editorial", "Salamandra");
            registro6.put("encuadernacion", " Tapa dura");
            registro6.put("precio", 150);
            registro6.put("id_categoria", 30);
            registro6.put("ci_autor", 1279458);



            registro7.put("isbn_libro", 788478886);
            registro7.put("nombre_libro", " Harry Potter y el cáliz de fuego");
            registro7.put("editorial", "Salamandra");
            registro7.put("encuadernacion", " Tapa dura");
            registro7.put("precio", 150);
            registro7.put("id_categoria", 30);
            registro7.put("ci_autor", 1279458);


            registro8.put("isbn_libro", 788436584);
            registro8.put("nombre_libro", "  Harry Potter y La Piedra Filosofal");
            registro8.put("editorial", "Salamandra");
            registro8.put("encuadernacion", " Tapa blanda");
            registro8.put("precio", 80);
            registro8.put("id_categoria", 30);
            registro8.put("ci_autor", 1279458);


            SQLiteDatabase bdEscritura = admin.getWritableDatabase();
            bdEscritura.insert("libros", null, registro1);
            bdEscritura.insert("libros", null, registro2);
            bdEscritura.insert("libros", null, registro3);
            bdEscritura.insert("libros", null, registro4);
            bdEscritura.insert("libros", null, registro5);
            bdEscritura.insert("libros", null, registro6);
            bdEscritura.insert("libros", null, registro7);
            bdEscritura.insert("libros", null, registro8);

        }

        Cursor fila2 =bd.rawQuery("select id from categoria",null);
        if(!fila2.moveToFirst()){

            ContentValues registro1c = new ContentValues();
            ContentValues registro2c = new ContentValues();
            ContentValues registro3c = new ContentValues();
            ContentValues registro4c = new ContentValues();


            //novela clasica 50
            //novela rosa 45
            //novela infantil juvenil 30
            //78 ficcion

            registro1c.put("id ", 50);
            registro1c.put("tipo", "Novela Clasica");

            registro2c.put("id", 45);
            registro2c.put("tipo", "Novela Rosa");

            registro3c.put("id", 30);
            registro3c.put("tipo", "Infantil-Juvenil");

            registro4c.put("id", 40);
            registro4c.put("tipo", "ficcion");


            SQLiteDatabase bdEscritura = admin.getWritableDatabase();
            bdEscritura.insert("categoria", null, registro1c);
            bdEscritura.insert("categoria", null, registro2c);
            bdEscritura.insert("categoria", null, registro3c);
            bdEscritura.insert("categoria", null, registro4c);

        }


      Cursor fila3 =bd.rawQuery("select IDautor from autor",null);
        if(!fila3.moveToFirst()){

            ContentValues registro1a = new ContentValues();
            ContentValues registro2a = new ContentValues();
            ContentValues registro3a = new ContentValues();
            ContentValues registro4a = new ContentValues();
            ContentValues registro5a = new ContentValues();
//  db.execSQL("create table  autor (id_autor int primary key, nombre_autor text)");

            registro1a.put("IDautor", 127533);
            registro1a.put("nombreautor", "F. Scott Fitzgerald");

            registro2a.put("IDautor", 13465);
            registro2a.put("nombreautor", "Oscar Wilde");

            registro3a.put("IDautor", 156558);
            registro3a.put("nombreautor", "Jane Austen");

            registro4a.put("IDautor", 827989);
            registro4a.put("nombreautor", "André Aciman");

            registro5a.put("IDautor", 1279458);
            registro5a.put("nombreautor", "J.K. Rowling");


            SQLiteDatabase bdEscritura = admin.getWritableDatabase();
            bdEscritura.insert("autor", null, registro1a);
            bdEscritura.insert("autor", null, registro2a);
            bdEscritura.insert("autor", null, registro3a);
            bdEscritura.insert("autor", null, registro4a);
            bdEscritura.insert("autor", null, registro5a);

        }

        bd.close();

        this.btautor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), autor.class );
                startActivity(i);

            }
        });

        this.bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), novelaclasica.class );
                startActivity(i);

            }
        });

        this.bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), novelarosa.class );
                startActivity(i);

            }
        });

        this.bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), infantil_juvenil.class );
                startActivity(i);

            }
        });

        this.bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), modificar.class );
                startActivity(i);

            }
        });

        this.bttitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), titulo.class );
                startActivity(i);

            }
        });



    }
}
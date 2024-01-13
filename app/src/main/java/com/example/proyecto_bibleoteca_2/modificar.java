package com.example.proyecto_bibleoteca_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class modificar extends AppCompatActivity {

    private EditText ed1,ed2,ed3,ed4,ed6;
    private Button btguardar,btmodificar,btbuscar,btresert;
    private Spinner spcategoria,spautores;
    ArrayList<String> listacategoria;
    ArrayList<Integer> idcategoria;
    ArrayList<String> listaautores;
    ArrayList<Integer> idautores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        this.ed1 = (EditText) findViewById(R.id.ed1);
        this.ed2 = (EditText) findViewById(R.id.ed2);
        this.ed3 = (EditText) findViewById(R.id.ed3);
        this.ed4 = (EditText) findViewById(R.id.ed4);
        this.ed6 = (EditText) findViewById(R.id.ed6);
        this.btguardar = (Button) findViewById(R.id.btguardar);
        this.btmodificar = (Button) findViewById(R.id.btmodificar);
        this.btbuscar = (Button) findViewById(R.id.btbuscar);
        this.btresert = (Button) findViewById(R.id.btresert);
        this.spcategoria = (Spinner) findViewById(R.id.spcategoria);
        this.spautores = (Spinner) findViewById(R.id.spautores);

        this.btresert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                startActivity(getIntent());

            }
        });

        llenarListacategorias();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.listacategoria);
        this.spcategoria.setAdapter(adaptador);

        llenarListaautores();
        ArrayAdapter<CharSequence> adaptador2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.listaautores);
        this.spautores.setAdapter(adaptador2);



        this.btmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adminSQL admin = new adminSQL(v.getContext(),"bibleoteca", null, 1);
                SQLiteDatabase bd = admin.getReadableDatabase();

                if(ed1.getText().length()>0 &ed2.getText().length()>0 &ed3.getText().length()>0 &ed4.getText().length()>0 &ed6.getText().length()>0  ){

                    int id = (int) spcategoria.getSelectedItemId();
                    int idCategoria = idcategoria.get(id);

                    int ci = (int) spautores.getSelectedItemId();
                    int ciAutor = idautores.get(ci);


                    ContentValues registroModificado = new ContentValues();

                    registroModificado.put("isbn_libro",Integer.parseInt(ed1.getText().toString()));
                    registroModificado.put("nombre_libro",ed2.getText().toString());
                    registroModificado.put("encuadernacion",ed3.getText().toString());
                    registroModificado.put("precio",Integer.parseInt(ed4.getText().toString()));
                    registroModificado.put("id_categoria",idCategoria);
                    registroModificado.put("editorial ",ed6.getText().toString());
                    registroModificado.put("ci_autor",ciAutor);

                    int isbn= Integer.parseInt(ed1.getText().toString());

                    bd.update("libros",registroModificado, "isbn_libro ="+isbn,null);


                    int cantRegistroModificado = bd.update("libros",registroModificado,"isbn_libro="+isbn,null);

                    if (cantRegistroModificado>=1){

                        Toast.makeText(v.getContext(),"Los cambios del libro se modificaron exitosamente", Toast.LENGTH_LONG).show();

                    }else
                    {
                        Toast.makeText(v.getContext(),"No se a encontrado un libro con el isbn ingresado", Toast.LENGTH_LONG).show();
                        limpiar();
                    }

                }else
                {
                    Toast.makeText(v.getContext(),"No se ingreso los valores", Toast.LENGTH_LONG).show();
                }
                bd.close();

            }
        });



        this.btbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adminSQL admin = new adminSQL(v.getContext(),"bibleoteca", null, 1);
                SQLiteDatabase bd = admin.getReadableDatabase();

                if(ed1.getText().length()>0){

                    int isbn = Integer.parseInt(ed1.getText().toString());

                    Cursor fila = bd.rawQuery("select nombre_libro, encuadernacion, precio,id_categoria,editorial,ci_autor from libros where isbn_libro =" + isbn , null);

                    if(fila.moveToFirst()){//si devuelve true es que la consulta recupera datos

                       ed2.setText(fila.getString(0));//0 seria = a carnet
                       ed3.setText(fila.getString(1));//1 seria = a nombre
                       ed4.setText(fila.getString(2));
                       int idCategoria = fila.getInt(3);
                       int ciautor = fila.getInt(5);

                        if (idCategoria==50){
                            spcategoria.setSelection(0);

                        } if (idCategoria==45){
                            spcategoria.setSelection(1);

                        }
                        if (idCategoria==30){
                            spcategoria.setSelection(2);

                        }
                        if (idCategoria==78){
                            spcategoria.setSelection(3);

                        }

                        ed6.setText(fila.getString(4));

                        if (ciautor==127533){
                            spautores.setSelection(0);

                        }if (ciautor==13465){
                            spautores.setSelection(1);

                        }if (ciautor==156558){
                            spautores.setSelection(2);

                        }if (ciautor==827989){
                            spautores.setSelection(3);

                        }if (ciautor==1279458){
                            spautores.setSelection(4);
                        }

                    }else
                    {
                        Toast.makeText(v.getContext(),"No se recupero informacion", Toast.LENGTH_LONG).show();

                    }

                }else
                {
                    Toast.makeText(v.getContext(),"No se ha ingreso ningun valor", Toast.LENGTH_LONG).show();
                }

                bd.close();

            }
        });



        this.btguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                adminSQL admin = new adminSQL(v.getContext(),"bibleoteca", null, 1);
                SQLiteDatabase bd = admin.getReadableDatabase();

                if(ed1.getText().length()>0 &ed2.getText().length()>0 &ed3.getText().length()>0 &ed4.getText().length()>0 ) {

                    int id = (int) spcategoria.getSelectedItemId();
                    int idCategoria = idcategoria.get(id);

                    int ci = (int) spautores.getSelectedItemId();
                    int ciAutor = idautores.get(ci);

                    ContentValues registro = new ContentValues();
                    registro.put("isbn_libro", Integer.parseInt(ed1.getText().toString()));
                    registro.put("nombre_libro", ed2.getText().toString());
                    registro.put("encuadernacion", ed3.getText().toString());
                    registro.put("precio", Integer.parseInt(ed4.getText().toString()));
                    registro.put("id_categoria ", idCategoria);
                    registro.put("editorial", ed6.getText().toString());
                    registro.put("ci_autor ", idCategoria);

                    bd.insert("libros", null, registro);

                    bd.close();

                    limpiar();
                    Toast.makeText(v.getContext(),"El registro se guardo exitosamente", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(v.getContext(),"No se ha ingreso algun valor", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void limpiar() {

        this.ed1.setText("");
        this.ed2.setText("");
        this.ed3.setText("");
        this.ed4.setText("");
        this.ed6.setText("");

    }
    public void llenarListacategorias() {

        adminSQL admin = new adminSQL(this,"bibleoteca", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();

        Cursor consultaid = bd.rawQuery("select id, tipo from categoria" , null);

        listacategoria = new ArrayList<String>();
        idcategoria = new ArrayList<Integer>();

        while (consultaid.moveToNext())
        {

            int idenificador = consultaid.getInt(0);
            String categoria = consultaid.getString(1);
            listacategoria.add(categoria);
            idcategoria.add(idenificador);

        }

        consultaid.close();
        bd.close();

    }


    public void llenarListaautores() {

        adminSQL admin = new adminSQL(this,"bibleoteca", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();

        Cursor consultaid = bd.rawQuery("select IDautor, nombreautor from autor" , null);

        listaautores = new ArrayList<String>();
        idautores = new ArrayList<Integer>();

        while (consultaid.moveToNext())
        {

            int idenificadorA = consultaid.getInt(0);
            String autores = consultaid.getString(1);
            listaautores.add(autores);
            idautores.add(idenificadorA);

        }

        consultaid.close();
        bd.close();

    }
}
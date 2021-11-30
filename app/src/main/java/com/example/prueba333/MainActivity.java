package com.example.prueba333;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button Voto,Resultado;
    RadioButton Nulo,Boric,Kast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resultado = (Button) findViewById(R.id.btn_resultado);
        Voto = (Button) findViewById(R.id.btn_voto);
        Nulo = (RadioButton) findViewById(R.id.radioButton);
        Boric = (RadioButton) findViewById(R.id.radioButton2);
        Kast = (RadioButton) findViewById(R.id.radioButton3);


        Voto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db;
                Dbhelper conn = new Dbhelper(getApplicationContext());
                db=conn.getWritableDatabase();
                ContentValues CV = new ContentValues();

                if(Nulo.isChecked()==false || Boric.isChecked()==false || Kast.isChecked()==false){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("ALERTA!! Su voto esta en blanco, Desea continuar?")
                            .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    SQLiteDatabase db;
                                    Dbhelper conn = new Dbhelper(getApplicationContext());
                                    db = conn.getReadableDatabase();
                                    db.insert("Voto",null,CV);
                                    Intent I = new Intent(getApplicationContext(),MainActivity2.class);
                                    startActivity(I);
                                }
                            })
                            .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    builder.create();
                    builder.show();


                }

                if(Nulo.isChecked()){
                    CV.put("VotoNulo",Nulo.getText().toString());
                    db.insert("Voto",null,CV);
                    Toast.makeText(getApplicationContext(),"Se ha guardado su voto",Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(getApplicationContext(),MainActivity2.class);
                    startActivity(I);
                }

                if(Boric.isChecked()){
                    CV.put("VotoBoric",Boric.getText().toString());
                    db.insert("Voto",null,CV);
                    Toast.makeText(getApplicationContext(),"Se ha guardado su voto",Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(getApplicationContext(),MainActivity2.class);
                    startActivity(I);
                }

                if(Kast.isChecked()){
                    CV.put("VotoKast",Kast.getText().toString());
                    db.insert("Voto",null,CV);
                    Toast.makeText(getApplicationContext(),"Se ha guardado su voto",Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(getApplicationContext(),MainActivity2.class);
                    startActivity(I);
                }
            }
        });

        Resultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(I);

            }
        });

    }
}


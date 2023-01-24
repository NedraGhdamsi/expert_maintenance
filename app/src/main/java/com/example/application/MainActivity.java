package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    Button btnclient,btncontrat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnclient=findViewById(R.id.btnclient);
        btncontrat=findViewById(R.id.btncontrat);
        db=openOrCreateDatabase("tp1",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS contrats (" +
                "id INTEGER primary key AUTOINCREMENT ," +
                "reference DECEMAL," +
                "datedebut DATE," +
                "datefin DATE," +
                "redevence DECEMAL," +
                "valsynch INT,"+
                "idcl INT,"+
                "nom VARCHAR ," +
                "FOREIGN KEY (idcl)REFERENCES client(id))");
        db.execSQL("CREATE TABLE IF NOT EXISTS client (id INTEGER   primary key AUTOINCREMENT ," +
                "nom VARCHAR ," +
                "adresse VARCHAR," +
                "tel VARCHAR," +
                "fax VARCHAR," +
                "email VARCHAR," +
                "contact VARCHAR," +
                "telcontact VARCHAR," +
                "valsynch INT )");

        db.close();
   btnclient.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
         Intent  i =new Intent(getApplicationContext(),Client.class);
           startActivity(i);
       }
   });

        btncontrat.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(getApplicationContext(),Contract.class);
            startActivity(i);
        }
    });
}}

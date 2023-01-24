package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class rech_client extends AppCompatActivity {
    EditText rechnom,idc,nom;
    ImageButton last,first,previous,next;
    Button rech,enr,annul;
    SQLiteDatabase db;
    String x;
    Cursor c1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rech_client);
        db = openOrCreateDatabase("tp1", MODE_PRIVATE, null);
        rechnom=findViewById(R.id.rechnom);
        rech=findViewById(R.id.rech);
        enr=findViewById(R.id.enr);
        annul=findViewById(R.id.annul);
        idc=findViewById(R.id.idc);
        nom=findViewById(R.id.nom);
        next=findViewById(R.id.next);
        first=findViewById(R.id.first);
        last=findViewById(R.id.last);
        previous=findViewById(R.id.previous);
        rech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1 = db.rawQuery("SELECT * FROM client WHERE nom like ?", new String[]{"%" + rechnom.getText().toString() + "%"});
                try {

                    if (c1.getCount() == 0) {
                        Toast.makeText(rech_client.this, "aucun client avec ce nom", Toast.LENGTH_SHORT).show();
                    } else {
                        c1.moveToFirst();
                        idc.setText(c1.getString(0));
                        nom.setText(c1.getString(1));

                    }
                } catch (Exception se) {
                    Toast.makeText(rech_client.this, "message", Toast.LENGTH_LONG).show();

                }
                ;
            }
        });
        annul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(getApplicationContext(),Contract.class);
                startActivity(i2);
            }
        });
        enr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=idc.getText().toString();
                String pnom= nom.getText().toString();
                Intent i =new Intent(getApplicationContext(),Contract.class);
                i.putExtra("id",id);
                i.putExtra("nom",pnom);
                setResult(5,i);
                finish();
            }
        });
    }
    }

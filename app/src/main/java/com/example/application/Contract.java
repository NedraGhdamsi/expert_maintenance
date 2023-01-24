package com.example.application;

import androidx.annotation.Nullable;
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

public class Contract extends AppCompatActivity {
Button rech,ajout,supp,modif,enr,annul,rechc;
EditText dated,datef,ref,red,client,idc;
ImageButton last,first,previous,next;
SQLiteDatabase db;
String x;
String idcl;
Cursor c1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);
        db = openOrCreateDatabase("tp1", MODE_PRIVATE, null);
        client=findViewById(R.id.client);
        idc=findViewById(R.id.idc);
       rechc=findViewById(R.id.rechc);
        rech=findViewById(R.id.rech);
        ajout=findViewById(R.id.ajout);
        enr=findViewById(R.id.enr);
        annul=findViewById(R.id.annul);
        supp=findViewById(R.id.supp);
        modif=findViewById(R.id.modif);
        dated=findViewById(R.id.dated);
       next=findViewById(R.id.next);
        first=findViewById(R.id.first);
        last=findViewById(R.id.last);
        previous=findViewById(R.id.previous);
        datef=findViewById(R.id.datefin);
        red=findViewById(R.id.red);
          ref=findViewById(R.id.ref);
        idc.setVisibility(View.INVISIBLE);
        enr.setVisibility(View.INVISIBLE);
        annul.setVisibility(View.INVISIBLE);
        ajout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dated.setText("");
                datef.setText("");
                ref.setText("");
                red.setText("");
                client.setText("");
                rechc.setVisibility(View.INVISIBLE);
                modif.setVisibility(View.INVISIBLE);
                ajout.setVisibility(View.INVISIBLE);
                supp.setVisibility(View.INVISIBLE);
                last.setVisibility(View.INVISIBLE);
                next.setVisibility(View.INVISIBLE);
                previous.setVisibility(View.INVISIBLE);
                first.setVisibility(View.INVISIBLE);
                rech.setVisibility(View.INVISIBLE);
                enr.setVisibility(View.VISIBLE);
                annul.setVisibility(View.VISIBLE);
                enr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db.execSQL("insert into contrats (id,reference,datedebut,datefin, redevence,nom,idcl)values(?,?,?,?,?,?,?) ;"
                                ,new String[]{null,ref.getText().toString(),dated.getText().toString(),datef.getText().toString(),red.getText().toString(),client.getText().toString(),idcl});

                        Toast.makeText(Contract.this," contrat enregistrer",Toast.LENGTH_SHORT).show();
                    }
                });
                annul.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        rechc.setVisibility(View.VISIBLE);
                        rech.setVisibility(View.VISIBLE);
                        ref.setVisibility(View.VISIBLE);
                        modif.setVisibility(View.VISIBLE);
                        ajout.setVisibility(View.VISIBLE);
                        supp.setVisibility(View.VISIBLE);
                        last.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        previous.setVisibility(View.VISIBLE);
                        first.setVisibility(View.VISIBLE);
                        enr.setVisibility(View.INVISIBLE);
                        annul.setVisibility(View.INVISIBLE);
                        dated.setText("");
                        datef.setText("");
                        ref.setText("");
                        red.setText("");
                        client.setText("");
                    }
                });


            }
        });
        supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x=c1.getString(0);
                db.execSQL("DELETE FROM contrats WHERE reference=?;",new String[]{ref.getText().toString()} );
                Toast.makeText(Contract.this, " contrat supprimer", Toast.LENGTH_SHORT).show();
                dated.setText("");
                datef.setText("");
                ref.setText("");
                client.setText("");
                red.setText("");
            }
        });
        modif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.setVisibility(View.INVISIBLE);
                rechc.setVisibility(View.INVISIBLE);
                modif.setVisibility(View.INVISIBLE);
                ajout.setVisibility(View.INVISIBLE);
                supp.setVisibility(View.INVISIBLE);
                last.setVisibility(View.INVISIBLE);
                next.setVisibility(View.INVISIBLE);
                previous.setVisibility(View.INVISIBLE);
                first.setVisibility(View.INVISIBLE);
                rech.setVisibility(View.INVISIBLE);
                enr.setVisibility(View.VISIBLE);
                annul.setVisibility(View.VISIBLE);
                enr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        x=c1.getString(0);
                        db.execSQL("Update contrats set datedebut=?,datefin=?, redevence=? ,nom=?;",
                                new String[]{dated.getText().toString(),datef.getText().toString(),red.getText().toString(),client.getText().toString()});
                        Toast.makeText(Contract.this, " modification enregistrer", Toast.LENGTH_SHORT).show();
                    }
                });
                annul.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        rechc.setVisibility(View.VISIBLE);
                        rech.setVisibility(View.VISIBLE);
                        ref.setVisibility(View.VISIBLE);
                        modif.setVisibility(View.VISIBLE);
                        ajout.setVisibility(View.VISIBLE);
                        supp.setVisibility(View.VISIBLE);
                        last.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        previous.setVisibility(View.VISIBLE);
                        first.setVisibility(View.VISIBLE);
                        enr.setVisibility(View.INVISIBLE);
                        annul.setVisibility(View.INVISIBLE);
                        dated.setText("");
                        datef.setText("");
                        ref.setText("");
                        red.setText("");
                        client.setText("");
                    }
                });

            }
        });
        rech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1=db.rawQuery("select * from contrats  where reference like ? ",new String[]{"%"+ref.getText().toString()+"%"});

                try {

                    if (c1.getCount() == 0) {
                        Toast.makeText(Contract.this, " aucun contrat", Toast.LENGTH_SHORT).show();
                    }

                   else  {
                       c1.moveToFirst();
                        dated.setText(c1.getString(2));
                        datef.setText(c1.getString(3));
                        red.setText(c1.getString(4));
                        client.setText(c1.getString(c1.getColumnIndexOrThrow("nom")));
                    }
                }
                catch (Exception se) {
                    Toast.makeText(Contract.this, "message", Toast.LENGTH_LONG).show();

                }
                ;
            }
        });
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1.moveToFirst();
                ref.setText(c1.getString(1));
                dated.setText(c1.getString(2));
                datef.setText(c1.getString(3));
                red.setText(c1.getString(4));
                client.setText(c1.getString(7));
                next.setEnabled(true);
            }

        });
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1.moveToLast();
                ref.setText(c1.getString(1));
                dated.setText(c1.getString(2));
                datef.setText(c1.getString(3));
                red.setText(c1.getString(4));
                client.setText(c1.getString(7));
                previous.setEnabled(true);
                next.setEnabled(false);
            }

        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1.moveToNext();
                ref.setText(c1.getString(1));
                dated.setText(c1.getString(2));
                datef.setText(c1.getString(3));
                red.setText(c1.getString(4));
                client.setText(c1.getString(7));
                previous.setEnabled(true);
                if (c1.isLast()){
                    next.setEnabled(false);
                }
            }

        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1.moveToPrevious();
                ref.setText(c1.getString(1));
                dated.setText(c1.getString(2));
                datef.setText(c1.getString(3));
                red.setText(c1.getString(4));
                client.setText(c1.getString(7));
                next.setEnabled(true);
                if (c1.isFirst()){
                    previous.setEnabled(false);
                }
            }

        });
        rechc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(getApplicationContext(),rech_client.class);
                startActivityForResult(i2,5);
            }
        });
        ;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==5){

            String message =data.getStringExtra("id");
            idcl=message;
            String messagenom=data.getStringExtra("nom");
            client.setText(messagenom);
            idc.setText(message);

            db = openOrCreateDatabase("tp1", MODE_PRIVATE, null);
            Cursor c= db.rawQuery("select * from contrats where idcl=?",new String[]{data.getStringExtra("id")});
            Toast.makeText(Contract.this, ""+c.getCount(), Toast.LENGTH_LONG).show();
            if (c.getCount()>0)
         {  c.moveToFirst();
             ref.setText(c.getString(1));
             dated.setText(c.getString(2));
             datef.setText(c.getString(3));
             red.setText(c.getString(4));
         }
        }
    }

}



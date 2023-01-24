package com.example.application;

import androidx.activity.result.contract.ActivityResultContracts;
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

public class Client extends AppCompatActivity {
    SQLiteDatabase db;
    Button btnsave, btnback,btnsupp,btnmodif,btnadd,rech;
    EditText nom, tel, fax, con, mail, telcon, adr;
    ImageButton next,first,previous,last;
    String x;
    Cursor c1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        db = openOrCreateDatabase("tp1", MODE_PRIVATE, null);
        next=findViewById(R.id.next);
        last=findViewById(R.id.last);
        first=findViewById(R.id.first);
        rech=findViewById(R.id.rech);
        previous=findViewById(R.id.previous);
        btnsave = findViewById(R.id.btnsave);
        btnsupp = findViewById(R.id.btnsupp);
        btnmodif = findViewById(R.id.btnmodif);
        btnadd = findViewById(R.id.btnadd);
        btnback = findViewById(R.id.btnback);
        nom = findViewById(R.id.nom);
        tel = findViewById(R.id.tel);
        adr = findViewById(R.id.adr);
        fax = findViewById(R.id.fax);
        con = findViewById(R.id.con);
        mail = findViewById(R.id.mail);
        telcon = findViewById(R.id.telcon);
        btnsave.setVisibility(View.INVISIBLE);
        btnback.setVisibility(View.INVISIBLE);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnmodif.setVisibility(View.INVISIBLE);
                btnadd.setVisibility(View.INVISIBLE);
                btnsupp.setVisibility(View.INVISIBLE);
                last.setVisibility(View.INVISIBLE);
                next.setVisibility(View.INVISIBLE);
                previous.setVisibility(View.INVISIBLE);
                first.setVisibility(View.INVISIBLE);
                rech.setVisibility(View.INVISIBLE);
                btnsave.setVisibility(View.VISIBLE);
                btnback.setVisibility(View.VISIBLE);
                btnsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String q = "insert into client values (" +
                                "null , '" +
                                nom.getText().toString()+"','"+
                                adr.getText().toString()+"','"+
                                tel.getText().toString()+"','"+
                                fax.getText().toString()+"','"+
                                mail.getText().toString()+"','"+
                                con.getText().toString()+"','"+
                                telcon.getText().toString()+"',"+
                                "1" +
                                " );";
                        db.execSQL(q);


                        Toast.makeText(Client.this,"enregistrer",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                btnsave.setVisibility(View.INVISIBLE);
                btnback.setVisibility(View.INVISIBLE);
                btnmodif.setVisibility(View.VISIBLE);
                btnadd.setVisibility(View.VISIBLE);
                btnsupp.setVisibility(View.VISIBLE);
                last.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                previous.setVisibility(View.VISIBLE);
                first.setVisibility(View.VISIBLE);
                rech.setVisibility(View.VISIBLE);
                nom.setText("");
                tel.setText("");
                telcon.setText("");
                adr.setText("");
                fax.setText("");
                con.setText("");
                mail.setText("");
            }
        });
        btnsupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x=c1.getString(0);
                db.execSQL("DELETE FROM client WHERE id=?;",new String[]{c1.getString(0)} );
                Toast.makeText(Client.this, " client supprimer", Toast.LENGTH_SHORT).show();
                nom.setText("");
                tel.setText("");
                telcon.setText("");
                adr.setText("");
                fax.setText("");
                con.setText("");
                mail.setText("");}});

        btnmodif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnmodif.setVisibility(View.INVISIBLE);
                btnadd.setVisibility(View.INVISIBLE);
                btnsupp.setVisibility(View.INVISIBLE);
                last.setVisibility(View.INVISIBLE);
                next.setVisibility(View.INVISIBLE);
                previous.setVisibility(View.INVISIBLE);
                first.setVisibility(View.INVISIBLE);
                rech.setVisibility(View.INVISIBLE);
                btnsave.setVisibility(View.VISIBLE);
                btnback.setVisibility(View.VISIBLE);
                btnsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        x=c1.getString(0);
                        db.execSQL("Update client set nom=?,adresse=?, tel=?,fax=? ,email=? ,contact=?,telcontact=?  ;",new String[]{nom.getText().toString(),adr.getText().toString(),tel.getText().toString(),
                                fax.getText().toString(),mail.getText().toString(),con.getText().toString(),telcon.getText().toString()});
                        Toast.makeText(Client.this, " client modifier", Toast.LENGTH_SHORT).show();

                    }
                });

        }});

         btnback.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View view) {
                    btnsave.setVisibility(View.INVISIBLE);
                    btnback.setVisibility(View.INVISIBLE);
                    btnmodif.setVisibility(View.VISIBLE);
                    btnadd.setVisibility(View.VISIBLE);
                    btnsupp.setVisibility(View.VISIBLE);
                    last.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                    previous.setVisibility(View.VISIBLE);
                    first.setVisibility(View.VISIBLE);
                    rech.setVisibility(View.VISIBLE);
                    nom.setText("");
                    tel.setText("");
                    telcon.setText("");
                    adr.setText("");
                    fax.setText("");
                    con.setText("");
                    mail.setText("");
                }
            });
        rech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1 = db.rawQuery("SELECT * FROM client WHERE nom like ? ",new String[]{"%"+nom.getText().toString()+"%"});
                try {
                    c1.moveToFirst();
                    if (c1.getCount() == 0) {
                        Toast.makeText(Client.this, " innotrouvable", Toast.LENGTH_SHORT).show();
                    }

                    else  {
                        nom.setText(c1.getString(1));
                        adr.setText(c1.getString(2));
                        tel.setText(c1.getString(3));
                       fax.setText(c1.getString(4));
                       mail.setText(c1.getString(5));
                        con.setText(c1.getString(6));
                        telcon.setText(c1.getString(7));
                }}
                catch (Exception se) {
                    Toast.makeText(Client.this, "innotrouvable", Toast.LENGTH_LONG).show();

                }
                ;
            }
        });
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1.moveToFirst();
                nom.setText(c1.getString(1));
                adr.setText(c1.getString(2));
                tel.setText(c1.getString(3));
                fax.setText(c1.getString(4));
                mail.setText(c1.getString(5));
                con.setText(c1.getString(6));
                telcon.setText(c1.getString(7));
                previous.setEnabled(false);
                next.setEnabled(true);
            }

        });
       last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1.moveToLast();
                nom.setText(c1.getString(1));
                adr.setText(c1.getString(2));
                tel.setText(c1.getString(3));
                fax.setText(c1.getString(4));
                mail.setText(c1.getString(5));
                con.setText(c1.getString(6));
                telcon.setText(c1.getString(7));
                previous.setEnabled(true);
                next.setEnabled(false);
            }

        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1.moveToNext();
                nom.setText(c1.getString(1));
                adr.setText(c1.getString(2));
                tel.setText(c1.getString(3));
                fax.setText(c1.getString(4));
                mail.setText(c1.getString(5));
                con.setText(c1.getString(6));
                telcon.setText(c1.getString(7));
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
                nom.setText(c1.getString(1));
                adr.setText(c1.getString(2));
                tel.setText(c1.getString(3));
                fax.setText(c1.getString(4));
                mail.setText(c1.getString(5));
                con.setText(c1.getString(6));
                telcon.setText(c1.getString(7));
                next.setEnabled(true);
                if (c1.isFirst()){
                    previous.setEnabled(false);
                }
            }

        });
    }
    }
    /*
    void saveClient(SQLiteDatabase db){
        if (op==1){
            SQLiteStatement r=db.compileStatement("select count(*) from client");
            double totalC=r.simpleQueryForLong();

            if (totalC>=1) {
                email = db.rawQuery("select email from client where email=?", new String[]{mail.getText().toString()});
                email.moveToFirst();
                if (email.getCount() > 0) {
                    if (!mail.getText().toString().equals(email.getString(0))) {
                        db.execSQL(String q = "insert into client values (" +
                                "null , '" +
                                nom.getText().toString()+"','"+
                                adr.getText().toString()+"','"+
                                tel.getText().toString()+"','"+
                                fax.getText().toString()+"','"+
                                mail.getText().toString()+"','"+
                                con.getText().toString()+"','"+
                                telcon.getText().toString()+"',"+
                                "1" +
                                " );";
                        db.execSQL(q);}
                        nom.setText("");
                    tel.setText("");
                    telcon.setText("");
                    adr.setText("");
                    fax.setText("");
                    con.setText("");
                    mail.setText("");
                        Toast.makeText(this, "successfull", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "Client existant", Toast.LENGTH_SHORT).show();
                         nom.setText("");
                    tel.setText("");
                    telcon.setText("");
                    adr.setText("");
                    fax.setText("");
                    con.setText("");
                    mail.setText("");
                    }


                }
                else{
                    db.execSQL("String q = "insert into client values (" +
                                "null , '" +
                                nom.getText().toString()+"','"+
                                adr.getText().toString()+"','"+
                                tel.getText().toString()+"','"+
                                fax.getText().toString()+"','"+
                                mail.getText().toString()+"','"+
                                con.getText().toString()+"','"+
                                telcon.getText().toString()+"',"+
                                "1" +
                                " );";
                        db.execSQL(q););
                    nom.setText("");
                    tel.setText("");
                    telcon.setText("");
                    adr.setText("");
                    fax.setText("");
                    con.setText("");
                    mail.setText("");
                    Toast.makeText(this, "successfull", Toast.LENGTH_SHORT).show();
                }

            }
            else {
                db.execSQL("String q = "insert into client values (" +
                                "null , '" +
                                nom.getText().toString()+"','"+
                                adr.getText().toString()+"','"+
                                tel.getText().toString()+"','"+
                                fax.getText().toString()+"','"+
                                mail.getText().toString()+"','"+
                                con.getText().toString()+"','"+
                                telcon.getText().toString()+"',"+
                                "1" +
                                " );";
                        db.execSQL(q););
                nom.setText("");
                    tel.setText("");
                    telcon.setText("");
                    adr.setText("");
                    fax.setText("");
                    con.setText("");
                    mail.setText("");
                Toast.makeText(this, "successfull", Toast.LENGTH_SHORT).show();
            }
        }
*/
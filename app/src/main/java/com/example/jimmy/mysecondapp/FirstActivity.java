package com.example.jimmy.mysecondapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.text.IDNA;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.text.BreakIterator;


public class FirstActivity extends AppCompatActivity {
    TextView Nom;
    TextView Prenom;
    RadioButton Homme;
    RadioButton Femme;
    Button Enter;
    private static final int DIALOG_ALERT = 10;

    //Note :
    //générer un APK
    //code source lisible
    //séparer les classes
    // deadline 25 mai
    //respecter slide 4

    public static final String BIERS_UPDATE = "com.octip.cours.inf4042_11.BIERS_UPDATE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Nom = findViewById(R.id.editText3);
        Prenom = findViewById(R.id.editText2);
        Homme = findViewById(R.id.radioButton);
        Femme = findViewById(R.id.radioButton2);
        Enter = findViewById(R.id.button);




        IntentFilter intentFilter = new IntentFilter(BIERS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(),intentFilter);


        Nom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Nom.setText("");
                if (Nom.getText().length() >= 10)Nom.setError("10 Caractères maximum");

            }
        });

        Prenom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Prenom.setText("");
                if (Prenom.getText().length() < 10)Prenom.setError("10 Caractères maximum");
            }
        });




        Homme.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Homme.isSelected() || Homme.isSelected()) {
                    Femme.setSelected(false);
                    Femme.setChecked(false);
                } else {
                    Homme.setSelected(true);
                    Homme.setChecked(false);
                }

            }

        });

        Femme.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Femme.isSelected() || Femme.isSelected()) {
                    Homme.setSelected(false);
                    Homme.setChecked(false);
                } else {
                    Femme.setSelected(true);
                    Femme.setChecked(false);
                }

            }

        });



       Enter.setOnClickListener(new OnClickListener() {



            @Override
            public void onClick(View view) {


                final String prenomsaisi = Prenom.getText().toString();
                final String nomsaisi = Nom.getText().toString();
                final String sexeh = Homme.getText().toString();
                final String sexef = Femme.getText().toString();


                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(FirstActivity.this);
                builder.setTitle("Confirmation");
                builder.setMessage("Êtes-vous bien " +prenomsaisi+" "+nomsaisi+"?");
                builder.setCancelable(false);

                builder.setPositiveButton("Yes",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which){

                        Toast.makeText(getApplicationContext(), "Bienvenue", Toast.LENGTH_SHORT).show();
                        // code ici
                        Intent i = new Intent(FirstActivity.this, ListProgramme.class);
                        //Intent i2 = new Intent(FirstActivity.this, profile.class);
                        if (((Nom.getText().length() < 14)) && (Prenom.getText().length() < 14) && (((RadioButton) findViewById(R.id.radioButton)).isChecked() ||((RadioButton) findViewById(R.id.radioButton2)).isChecked() ) ) {



                            stopService(new Intent(getApplicationContext(), MyService.class));

                            if (((RadioButton) findViewById(R.id.radioButton)).isChecked()) {
                                Toast.makeText(getApplicationContext(), "" + prenomsaisi + "\n" + nomsaisi + "\n" + Homme.getText(), Toast.LENGTH_LONG).show();
                                i.putExtra("homme", sexeh);
                            } else {
                                Toast.makeText(getApplicationContext(), "" + prenomsaisi + "\n" + nomsaisi + "\n" + Femme.getText(), Toast.LENGTH_LONG).show();
                                i.putExtra("homme", sexef);
                            }

                            i.putExtra("lenom", nomsaisi);
                            i.putExtra("leprenom", prenomsaisi);



                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "14 Caracteres maximum", Toast.LENGTH_LONG).show();

                        }

                  }

                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), "Veuillez mettre vos identités.", Toast.LENGTH_SHORT).show();
                        // code ici

                    }
                });

                android.app.AlertDialog alert = builder.create();
                alert.show();

            }

        });



    }
}

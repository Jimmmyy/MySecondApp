package com.example.jimmy.mysecondapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Welcome extends AppCompatActivity {
    private String leprenom, lenom, lesexe, jstxt;
    private TextView textprenom, textnom, textsexe, jss;
    Button alertDialogButton;
    String json = null;
    InputStream js = null;

    public JSONArray getBiersFromFile(){
        try {

            InputStream js = new FileInputStream(getCacheDir()+ "/" + "bieres.json");
            byte[] buffer = new byte[js.available()];
            js.read(buffer);
            js.close();

            Log.i("sos","coca");
            json = new String(buffer,"UTF-8");
            return new JSONArray(new String(buffer,"UTF-8"));

        } catch ( IOException | JSONException e) {
            e.printStackTrace();
            return  new JSONArray();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        alertDialogButton = findViewById(R.id.button2);






        leprenom = getIntent().getStringExtra("leprenom");
        lenom = getIntent().getStringExtra("lenom");
        lesexe = getIntent().getStringExtra("homme");


        textnom=findViewById(R.id.textView3);
        textprenom=findViewById(R.id.textView4);
        textsexe=findViewById(R.id.textView5);
        jss=findViewById(R.id.textView6);

        textnom.setText(lenom);
        textprenom.setText(leprenom);

        getBiersFromFile();





        alertDialogButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                AlertDialog.Builder builder = new AlertDialog.Builder(Welcome.this);
                builder.setTitle("TITRE");
                builder.setMessage("Test");
                builder.setCancelable(false);

                builder.setPositiveButton("Yes",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which){

                Toast.makeText(getApplicationContext(), "salut", Toast.LENGTH_SHORT).show();
                getBiersFromFile();
            }

        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                // code ici
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

});




    }
}
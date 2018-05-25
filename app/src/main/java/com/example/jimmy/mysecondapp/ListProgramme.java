package com.example.jimmy.mysecondapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ListProgramme extends AppCompatActivity {

    List<DataItem> lstData;
    private String leprenom, lenom, lesexe, jstxt;
    private TextView textprenom, textnom, textsexe, jss;

    Notification.Builder notification;
    private static final int uniqueID = 2223;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_programme);

        leprenom = getIntent().getStringExtra("leprenom");
        lenom = getIntent().getStringExtra("lenom");
        lesexe = getIntent().getStringExtra("homme");


        textnom=findViewById(R.id.Titre_1);
        textsexe=findViewById(R.id.Titre_2);

        textnom.setText(lenom);
        textsexe.setText(lesexe);




        lstData = new ArrayList<>();
        lstData.add(new DataItem(R.drawable.haut));
        lstData.add(new DataItem(R.drawable.bas));
        lstData.add(new DataItem(R.drawable.abdos));

        ListView listview = findViewById(R.id.listview);

        Adapter adapter = new Adapter(this, R.layout.layout_listitem,lstData);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.i("Position ",""+position);
                if (position == 0){
                    openProgrammeActivity();
                }
                if (position == 1){
                    openProgrammeBasActivity();
                }
                if (position == 2){
                    openProgrammeAbdosActivity();
                }
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_home:
                openListProgramme();
                return true;
            case R.id.action_propos:
                openProposActivity();
                return true;
            case R.id.action_profil:
                openProfileActivity();
                return true;
            case R.id.action_haut:
                openProgrammeActivity();
                return true;
            case R.id.action_bas:
                openProgrammeBasActivity();
                return true;
            case R.id.action_abs:
                openProgrammeAbdosActivity();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openListProgramme(){
        Intent intent = new Intent(this, ListProgramme.class);
        startActivity(intent);
    }

    public void openProgrammeActivity(){
        Intent intent = new Intent(this, Programme.class);
        startActivity(intent);
    }

    public void openProgrammeAbdosActivity(){
        Intent intent = new Intent(this, Programme_abdos.class);
        startActivity(intent);
    }

    public void openProgrammeBasActivity(){
        Intent intent = new Intent(this, programme_bas.class);
        startActivity(intent);
    }

    public void openProposActivity(){
        Intent intent = new Intent(this, Credit.class);
        startActivity(intent);
    }

    public void openProfileActivity(){
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }
}

package com.example.jimmy.mysecondapp;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Credit extends AppCompatActivity {

    InputStream js = null;

    private static final String URL_DATA = "http://www.json-generator.com/api/json/get/bVOmVkHKcy?indent=3";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        loadRecyclerViewData();
    }
    private void loadRecyclerViewData(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();
        GetBiersService.startActionBiers(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {


                       // JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = new JSONArray(s);

                        for(int i=0; i<array.length();i++){
                            JSONObject o = array.getJSONObject(i);
                            ListItem item = new ListItem(
                                    o.getString("name"),
                                    o.getString("description"),
                                    o.getString("image")



                            );
                            listItems.add(item);
                        }

                        adapter = new Adapter2(listItems, getApplicationContext());
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), volleyError.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
/*

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
    }*/

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

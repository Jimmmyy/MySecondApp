package com.example.jimmy.mysecondapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class profile extends YouTubeBaseActivity {

    Button b;

    private String leprenom, lenom, lesexe;
    private TextView textprenom, textnom, textsexe;
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        /*
        leprenom = getIntent().getStringExtra("leprenom");
        lenom = getIntent().getStringExtra("lenom");
        lesexe = getIntent().getStringExtra("homme");

        textnom=findViewById(R.id.profil_info);
        textprenom=findViewById(R.id.profil_info2);
        textsexe=findViewById(R.id.profil_infoSx);

        textnom.setText(lenom);
        textprenom.setText(leprenom);
        textsexe.setText(lesexe);*/

        youTubePlayerView = findViewById(R.id.youtube_view);

        onInitializedListener = new YouTubePlayer.OnInitializedListener(){
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,YouTubePlayer youTubePlayer,boolean b){
                youTubePlayer.loadVideo("EykWcFEtFqo");

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult YoutubeInitialisation){


            }
        };

        b = (Button) findViewById(R.id.button3);
        b.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick (View view){
                youTubePlayerView.initialize("\n" +
                        "AIzaSyAI5HaYbf_tqQJD7TnZLOi5PGGhk6OKWRA",onInitializedListener);
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

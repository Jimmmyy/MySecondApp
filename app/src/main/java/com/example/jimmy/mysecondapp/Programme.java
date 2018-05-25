package com.example.jimmy.mysecondapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Programme extends YouTubeBaseActivity implements  YouTubePlayer.OnInitializedListener {



    public static final String API_KEY = "AIzaSyAI5HaYbf_tqQJD7TnZLOi5PGGhk6OKWRA";
    public static final String VIDEO_ID = "kIVxdIWy7Eo";
    /*
    Button b;
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programme);


        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view2);
        youTubePlayerView.initialize(API_KEY, this);
    }

            @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult YoutubeInitialisation){
            Toast.makeText(this, "Failured to Initialize", Toast.LENGTH_LONG).show();

    }
      // onInitializedListener = new YouTubePlayer.OnInitializedListener(){
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored){
                player.setPlayerStateChangeListener(playerStateChangeListener);
                player.setPlaybackEventListener(playbackEventListener);
                if(!wasRestored) {
                    player.cueVideo (VIDEO_ID);
                }
            }


        private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener(){

        @Override
            public void onBuffering(boolean arg0){

        }
        @Override
            public void onPaused(){

        }
            @Override
            public void onPlaying(){

            }

            @Override
            public void onSeekTo(int arg0){

            }
            @Override
            public void onStopped(){

            }

        };
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener(){

        @Override
        public void onAdStarted(){

        }
        @Override
        public void onError(YouTubePlayer.ErrorReason arg0){

        }
        @Override
        public void onLoaded(String arg0){

        }

        @Override
        public void onLoading(){

        }
        @Override
        public void onVideoEnded(){

        }
        @Override
        public void onVideoStarted(){

        }


    };
/*
        b = (Button) findViewById(R.id.button3);
        b.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick (View view){
                youTubePlayerView.initialize("\n" +
                        "AIzaSyAI5HaYbf_tqQJD7TnZLOi5PGGhk6OKWRA",onInitializedListener);
            }

        });

    }
*/



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

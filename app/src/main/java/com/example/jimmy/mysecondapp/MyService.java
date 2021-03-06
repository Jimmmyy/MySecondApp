package com.example.jimmy.mysecondapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

/**
 * Created by Jimmy on 09/05/2018.
 */

public class MyService extends Service {
    private MediaPlayer player;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
    return null;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        player = MediaPlayer.create(getApplicationContext(), R.raw.music);
        player.setLooping(true);
        player.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }
}

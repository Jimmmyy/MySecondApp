package com.example.jimmy.mysecondapp;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetBiersService extends IntentService {


    // Service du TD qui nous servira pour le projet
    private static final String ACTION_get_all_biers = "com.example.jimmy.mysecondapp.action.get_all_biers ";
    private static final String ACTION_BAZ = "com.example.jimmy.mysecondapp.action.BAZ";

    Notification.Builder notification;
    private static final int uniqueID = 2223;



    public GetBiersService() {
        super("GetBiersService");
    }

    public static void startActionBiers(Context context) {

        Intent intent = new Intent(context, GetBiersService.class);

        intent.setAction(ACTION_get_all_biers);

        Log.i("TEST","startActionBiers");
        context.startService(intent);

    }


    // TODO: Customize helper method
    public static void startActionBaz(Context context) {
        Intent intent = new Intent(context, GetBiersService.class);
        intent.setAction(ACTION_BAZ);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_get_all_biers.equals(action)) {
                handleActionBiers();
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(FirstActivity.BIERS_UPDATE));

            } else if (ACTION_BAZ.equals(action)) {
            }
        }
    }

    private void copyInputStreamToFile(InputStream in, File file){
       try {
           OutputStream out = new FileOutputStream(file);
           byte[] buf = new byte[1024];
           int len;
           while ((len = in.read(buf)) > 0) {
               out.write(buf, 0, len);
           }
           out.close();
           in.close();
       } catch (Exception e){
           e.printStackTrace();
       }

    }


    private void handleActionBiers() {

        Log.i("TEST","info msgggggggg");
        URL url = null;
        try {
            url = new URL("http://www.json-generator.com/api/json/get/ceUMimfVTS?indent=3");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if(HttpURLConnection.HTTP_OK == conn.getResponseCode()){
                copyInputStreamToFile(conn.getInputStream(),
                        new File(getCacheDir(), "bieres.json"));
                Log.d("TEST", "URL OK");
                notification = new Notification.Builder(this);
                notification.setAutoCancel(true);

                notification.setSmallIcon(R.drawable.logo_dra);
                notification.setTicker("This is the ticker");
                notification.setWhen(System.currentTimeMillis());
                notification.setContentTitle("StreetWorkout ESIEA");
                notification.setContentText("Download complete !");

                Intent intent= new Intent(getApplicationContext(), ListProgramme.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(pendingIntent);

                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(uniqueID,notification.build());


            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();;
        }
       // throw new UnsupportedOperationException("Not yet implemented");

    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz() {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

package com.example.ljaketremindiestage.sensorcollectorsodifrance2;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollector;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.utils.CapteursUtils;

import java.util.ArrayList;
import java.util.List;

public class CollectorService extends Service {
    public static final String LOG_TAG = CollectorService.class.getSimpleName();
    private final IBinder mBinder = new CollectorServiceBinder();
    SensorManager sensorManager;
    StringBuffer sensorDesc;
    List<CapteurCollector> capteurCollectorList = new ArrayList<CapteurCollector>();
    List<Sensor> sensorList;

    @Override
    public void onCreate() {
        super.onCreate();
        // Lancer la collecte
        launchSensorCollection();
        Log.i(LOG_TAG, "Service onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "Service onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Used only in case of bound services.
        Log.i(LOG_TAG, "Service onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(LOG_TAG, "Service onRebind");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(LOG_TAG, "Service onStartCommand numero " + startId);
        if (intent.getAction().equals(CapteursUtils.STARTFOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "Received Start Foreground Intent ");
            Intent notificationIntent = new Intent(this, CollectorActivity.class);
            notificationIntent.setAction(CapteursUtils.MAIN_ACTION);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
            /*
            Intent previousIntent = new Intent(this, CollectorService.class);
            previousIntent.setAction(CapteursUtils.PREV_ACTION);
            PendingIntent ppreviousIntent = PendingIntent.getService(this, 0, previousIntent, 0);

            Intent playIntent = new Intent(this, CollectorService.class);
            playIntent.setAction(CapteursUtils.PLAY_ACTION);
            PendingIntent pplayIntent = PendingIntent.getService(this, 0, playIntent, 0);

            Intent nextIntent = new Intent(this, CollectorService.class);
            nextIntent.setAction(CapteursUtils.NEXT_ACTION);
            PendingIntent pnextIntent = PendingIntent.getService(this, 0, nextIntent, 0);
            */
            Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sodifrance);

            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle("Sensor Collector Sodifrance")
                    .setTicker("Sensor Collector Sodifrance")
                    .setContentText(sensorDesc)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, false))
                    .setContentIntent(pendingIntent)
                    .setOngoing(true)
                    //.addAction(android.R.drawable.ic_media_previous, "Previous", ppreviousIntent)
                    //.addAction(android.R.drawable.ic_media_play, "Play", pplayIntent)
                    //.addAction(android.R.drawable.ic_media_next, "Next", pnextIntent)
                    .build();
            startForeground(CapteursUtils.FOREGROUND_SERVICE, notification);
        } /*else if (intent.getAction().equals(CapteursUtils.PREV_ACTION)) {
            Log.i(LOG_TAG, "Clicked Previous");
        } else if (intent.getAction().equals(CapteursUtils.NEXT_ACTION)) {
            Log.i(LOG_TAG, "Clicked Next");
        } else if (intent.getAction().equals(CapteursUtils.PLAY_ACTION)) {
            Log.i(LOG_TAG, "Clicked Play");
        } */ else if (intent.getAction().equals(CapteursUtils.STOPFOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "Received Stop Foreground Intent");
            for (CapteurCollector capteurCollector : capteurCollectorList) {
                sensorManager.unregisterListener(capteurCollector, capteurCollector.getSensor());
            }
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
    }

    /**
     * Lister les capteurs existant
     * <p>
     * Trouve la liste de tous les capteurs existants, trouve un capteur spécifique ou l'ensemble des capteurs d'un type fixé.
     */
    private void launchSensorCollection() {
        // Instanicer le SensorManager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // Trouver tous les capteurs de l'appareil :
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        CapteurCollector capteurCollector;
        sensorDesc = new StringBuffer();
        // pour chaque capteur trouvé, construire sa chaîne descriptive
        for (Sensor sensor : sensorList) {
            capteurCollector = CapteursUtils.getCapeursCollectorBySensor(sensor);
            if (capteurCollector != null) {
                //Enregistrement du capteur
                capteurCollectorList.add(capteurCollector);
                sensorManager.registerListener(capteurCollector, capteurCollector.getSensor(), SensorManager.SENSOR_DELAY_NORMAL);
                Log.d(LOG_TAG, capteurCollector.getName());
            }
        }
        sensorDesc.append("Nombre de capteurs détectés : " + capteurCollectorList.size());
        Log.d(LOG_TAG, sensorDesc.toString());
    }

    public class CollectorServiceBinder extends Binder {
        public CollectorService getService() {
            return CollectorService.this;
        }
    }

}

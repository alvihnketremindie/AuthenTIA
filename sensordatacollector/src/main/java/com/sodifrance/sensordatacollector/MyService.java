package com.sodifrance.sensordatacollector;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static com.sodifrance.sensordatacollector.CreateCSVFile.writeToFileCSV;
import static com.sodifrance.sensordatacollector.zipCSV.fileZipped;
import static com.sodifrance.sensordatacollector.zipCSV.getDateZIP;
import static com.sodifrance.sensordatacollector.zipCSV.zip;

public class MyService extends Service implements SensorEventListener {
    public MyService() {
    }



    private SensorManager sensorManager;
    private ArrayList<Sensor> sensorList;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        sensorList = new ArrayList<>();
        sensorList.addAll(sensorManager.getSensorList(Sensor.TYPE_ALL));
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(!fileZipped(getDateZIP())){
            zip(getDateZIP());
            try {
                writeToFileCSV(sensorEvent.sensor.getName(), System.currentTimeMillis(), sensorEvent.values, getApplicationContext(), false);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                writeToFileCSV(sensorEvent.sensor.getName(), System.currentTimeMillis(), sensorEvent.values, getApplicationContext(), true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public int onStartCommand(Intent intent, int flags, int startId){
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        sensorList = new ArrayList<>();
        sensorList.addAll(sensorManager.getSensorList(Sensor.TYPE_ALL));
        for( Sensor sensor : sensorList) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        return START_STICKY;
    }
}
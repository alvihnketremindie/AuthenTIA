package com.example.ljaketremindiestage.sensorcollectorsodifrance2;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ljaketremindiestage.sensorcollectorsodifrance2.manager.SensorDataManager;

import java.util.List;
import java.util.Map;

public class SensorManagerActivity extends AppCompatActivity {
    private List<Sensor> capteursDisponibles;
    // The sensor data manager
    SensorDataManager sensorDataManager;
    Map<Integer, SensorEventListener> sensorEventListenerMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instanicer le sensorDataManager
       sensorDataManager  = new SensorDataManager(this);
        // Faire la liste des capteurs de l'appareil
        capteursDisponibles = sensorDataManager.getAvailableSensors();
        sensorEventListenerMap = sensorDataManager.getSensorEventListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (Sensor capteurs : capteursDisponibles) {
            sensorDataManager.registerSensorEventListener(capteurs.getType());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorDataManager.unregisterAllSensorEventListeners();
    }
}

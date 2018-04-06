package com.example.ljaketremindiestage.sensorcollectorsodifrance2.manager;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.example.ljaketremindiestage.sensorcollectorsodifrance2.donnees.CapteursData;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.donnees.CapteursDataSet;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.logger.DataInternalFileStorage;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.utils.CapteursUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SensorDataManager {
    public static final String TAG = SensorDataManager.class.getSimpleName();

    private SensorManager sensorManager;

    private Map<Integer, CapteursDataSet> sensorDataBatches;
    private Map<Integer, SensorEventListener> sensorEventListeners;

    private List<Sensor> availableSensors;

    private DataInternalFileStorage dataInternalFileStorage;

    public SensorDataManager(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        initializeSensorEventListeners();
        initializeSensorDataBatches();
        dataInternalFileStorage = new DataInternalFileStorage(context);
    }

    private void initializeSensorEventListeners() {
        sensorEventListeners = new HashMap<>();
    }

    private void initializeSensorDataBatches() {
        sensorDataBatches = new HashMap<>();
        availableSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : availableSensors) {
            sensorDataBatches.put(sensor.getType(), getCapteursDataSet(sensor.getType()));
        }
    }

    public void registerSensorEventListener(int sensorType) {
        registerSensorEventListener(sensorManager.getDefaultSensor(sensorType));
    }

    public void registerSensorEventListener(Sensor sensor) {
        if (sensor == null) {
            Log.w(TAG, "Impossible d'enregister un capteur 'null'");
            return;
        }

        if (hasRegisteredSensorEventListener(sensor.getType())) {
            return;
        }
        Log.v(TAG, "Enregistrement d'un listener pour le capteur " + sensor.getType() + " - " + sensor.getName());
        sensorManager.registerListener(getSensorEventListener(sensor.getType()), sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregisterAllSensorEventListeners() {
        //List<Sensor> availableSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : availableSensors) {
            unregisterSensorEventListener(sensor.getType());
        }
    }

    public void unregisterSensorEventListener(int sensorType) {
        if (hasRegisteredSensorEventListener(sensorType)) {
            Log.v(TAG, "Annulation de l'enregistrement du listener pour le capteur " + sensorType);
            sensorManager.unregisterListener(sensorEventListeners.get(sensorType));
            sensorEventListeners.put(sensorType, null);
        }
    }

    public boolean hasRegisteredSensorEventListener(int sensorType) {
        return sensorEventListeners.get(sensorType) != null;
    }

    public SensorEventListener getSensorEventListener(int sensorType) {
        SensorEventListener sensorEventListener = sensorEventListeners.get(sensorType);
        if (sensorEventListener == null) {
            sensorEventListener = createSensorEventListener(sensorType);
            sensorEventListeners.put(sensorType, sensorEventListener);
        }
        return sensorEventListener;
    }

    private SensorEventListener createSensorEventListener(final int sensorType) {
        return new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event.sensor.getType() != Sensor.TYPE_ACCELEROMETER) {
                    float[] values = new float[event.values.length];
                    System.arraycopy(event.values, 0, values, 0, event.values.length);
                    String name = CapteursUtils.getStrType(event.sensor.getType());
                    //CapteursData data = new CapteursData(name, values, event.timestamp);
                    //getCapteursDataSet(sensorType).addData(new CapteursData(name, values, event.timestamp));
                    getCapteursDataSet(sensorType).addData(new CapteursData(name, values, event.timestamp), dataInternalFileStorage);
                    /*int taille = event.values.length;
                    for(float val : event.values) {
                        Log.d("VerifInternalFloatVal", name+" == "+val+" , taille = "+taille);
                    }*/
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    public CapteursDataSet getCapteursDataSet(int sensorType) {
        CapteursDataSet capteursDataSet = sensorDataBatches.get(sensorType);
        if (capteursDataSet == null) {
            capteursDataSet = createCapteursDataSet(sensorType);
            sensorDataBatches.put(sensorType, capteursDataSet);
        }
        return capteursDataSet;
    }

    private CapteursDataSet createCapteursDataSet(int sensorType) {
        Sensor sensor = sensorManager.getDefaultSensor(sensorType);
        if (sensor == null) {
            return null;
        }
        String sensorName = sensor.getName();
        CapteursDataSet dataBatch = new CapteursDataSet(sensorName);
        dataBatch.setType(sensorType);
        return dataBatch;
    }

    /**
     * Getter & Setter
     */
    public SensorManager getSensorManager() {
        return sensorManager;
    }

    public void setSensorManager(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    public Map<Integer, CapteursDataSet> getAllCapteursDataSet() {
        return sensorDataBatches;
    }

    public void setAllCapteursDataSet(Map<Integer, CapteursDataSet> sensorDataBatches) {
        this.sensorDataBatches = sensorDataBatches;
    }

    public Map<Integer, SensorEventListener> getSensorEventListeners() {
        return sensorEventListeners;
    }

    public void setSensorEventListeners(Map<Integer, SensorEventListener> sensorEventListeners) {
        this.sensorEventListeners = sensorEventListeners;
    }

    /**
     * Lister les capteurs existant sur le mobile
     * <p>
     * Trouve la liste de tous les capteurs existants, trouve un capteur spécifique ou l'ensemble des capteurs d'un type fixé.
     */
    public List<Sensor> getAvailableSensors() {
        return availableSensors;
    }
}

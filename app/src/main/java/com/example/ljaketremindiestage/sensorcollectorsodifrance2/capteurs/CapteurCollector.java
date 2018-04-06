package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import com.example.ljaketremindiestage.sensorcollectorsodifrance2.donnees.CapteursData;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.logger.DataInternalFileStorage;

abstract public class CapteurCollector implements SensorEventListener {

    protected Sensor sensor;
    protected int type;
    protected String name;
    protected String filename;

    protected DataInternalFileStorage dataInternalFileStorage;

    protected CapteursData previousCapteursData = null;

    public CapteurCollector(Sensor sensor) {
        this.sensor = sensor;
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = new float[event.values.length];
        System.arraycopy(event.values, 0, values, 0, event.values.length);
        long time = event.timestamp;
        CapteursData capteursData = new CapteursData(name, values, time);
        if(!capteursData.equals(previousCapteursData)) {
            previousCapteursData = capteursData;
            variationValeurCapteur(values, time);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * Cette methode est appele a chaque fois que les valeurs du capteurs change
     * @param values
     * @param time
     */
    public abstract void variationValeurCapteur(float[] values, long time);

    public void setDataInternalFileStorage(DataInternalFileStorage dataInternalFileStorage) {
        this.dataInternalFileStorage = dataInternalFileStorage;
    }

    public void saveInFile(String toEnreg) {
        dataInternalFileStorage.ecrire(filename, toEnreg+"\r\n");
        Log.d(filename,toEnreg);
    }

    public Sensor getSensor() {
        return sensor;
    }
}

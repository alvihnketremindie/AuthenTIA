package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.example.ljaketremindiestage.sensorcollectorsodifrance2.donnees.CapteursData;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.logger.DataInternalFileStorage;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.manager.LogInFileManager;

import java.io.File;

abstract public class CapteurCollector implements SensorEventListener {

    protected Sensor sensor;
    protected int type;
    protected String name;
    protected String filename;
    protected String dataToWrite;
    protected CapteursData previousCapteursData = null;


    protected DataInternalFileStorage dataInternalFileStorage;
    protected File mFile = null;
    protected String pathdir;


    public CapteurCollector(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = new float[event.values.length];
        System.arraycopy(event.values, 0, values, 0, event.values.length);
        //long time = event.timestamp;
        long time = System.currentTimeMillis();
        CapteursData capteursData = new CapteursData(name, values, time);
        if (!capteursData.equals(previousCapteursData)) {
            previousCapteursData = capteursData;
            variationValeurCapteur(values, time);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * Cette methode est appele a chaque fois que les valeurs du capteurs change
     *
     * @param values
     * @param time
     */
    public abstract void variationValeurCapteur(float[] values, long time);

    public void setDataInternalFileStorage(DataInternalFileStorage dataInternalFileStorage) {
        this.dataInternalFileStorage = dataInternalFileStorage;
    }

    public void setPathdir(String pathdir) {
        this.pathdir = pathdir.trim();
    }

    public void saveInFile(String toEnreg) {
        //*
        dataToWrite = toEnreg;
        //*/
        //*
        //filename = formater.format(new Date())+"_"+filename;
        //System.out.println(formater.format(aujourdhui));
        //*/
        //*
        new Thread(new Runnable() {
            public void run() {
                LogInFileManager logInFileManager = new LogInFileManager();
                //logInFileManager.saveInFileExternal(CapteursUtils.formater.format(new Date())+"_"+filename.trim(), dataToWrite);
                logInFileManager.saveInFileExternal(filename.trim(), dataToWrite);
            }
        }).start();
        //*/
        /*
        dataInternalFileStorage.ecrire(filename, toEnreg+"\r\n");
        Log.d(filename,toEnreg);
        */
        //logInFileManager.saveInFileExternal(filename.trim(), dataToWrite);
    }

    public Sensor getSensor() {
        return sensor;
    }

    public String getName() {
        return name;
    }
}

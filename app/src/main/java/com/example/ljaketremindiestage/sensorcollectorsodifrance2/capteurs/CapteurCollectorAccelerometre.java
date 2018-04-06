package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

import com.example.ljaketremindiestage.sensorcollectorsodifrance2.donnees.CapteursData;

public class CapteurCollectorAccelerometre extends CapteurCollector {

    public CapteurCollectorAccelerometre(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_ACCELEROMETER;
        name = "Acceleromertre";
        filename = "accelerometer.csv";
    }

    @Override
    public void variationValeurCapteur(float[] values, long time) {
        saveInFile(time+";"+values[0]+";"+values[1]+";"+values[2]);
    }
}

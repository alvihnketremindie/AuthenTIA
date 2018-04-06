package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorGravite extends CapteurCollector {
    public CapteurCollectorGravite(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_GRAVITY;
        name = "Gravite";
        filename = "gravity.csv";
    }

    @Override
    public void variationValeurCapteur(float[] values, long time) {
        saveInFile(time+";"+values[0]);
    }
}

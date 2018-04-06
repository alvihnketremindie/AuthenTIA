package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorProximite extends CapteurCollector {
    public CapteurCollectorProximite(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_PROXIMITY;
        name = "Proximite";
        filename = "proxymity.csv";
    }

    @Override
    public void variationValeurCapteur(float[] values, long time) {
        saveInFile(time+";"+values[0]);
    }
}

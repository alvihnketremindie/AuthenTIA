package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorLuminosite extends CapteurCollector {
    public CapteurCollectorLuminosite(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_LIGHT;
        name = "Luminosite";
        filename = "light.csv";
    }

    @Override
    public void variationValeurCapteur(float[] values, long time) {
        saveInFile(time+";"+values[0]);
    }
}

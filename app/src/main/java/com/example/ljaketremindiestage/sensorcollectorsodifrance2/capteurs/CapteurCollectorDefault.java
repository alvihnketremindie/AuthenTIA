package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorDefault extends CapteurCollector {

    public CapteurCollectorDefault(Sensor sensor) {
        super(sensor);
        type = -1;
        name = "Inconnu";
        filename = "undefined.csv";
    }

    @Override
    public void variationValeurCapteur(float[] values, long time) {

    }
}

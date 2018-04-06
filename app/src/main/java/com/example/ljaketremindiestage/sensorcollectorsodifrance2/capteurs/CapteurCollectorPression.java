package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorPression extends CapteurCollector {

    public CapteurCollectorPression(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_PRESSURE;
        name = "Pression";
        filename = "pressure.csv";
    }

    @Override
    public void variationValeurCapteur(float[] values, long time) {
        saveInFile(time+";"+values[0]);
    }
}

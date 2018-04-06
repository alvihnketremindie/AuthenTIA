package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorTemperature extends CapteurCollector {
    public CapteurCollectorTemperature(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_TEMPERATURE;
        name = "Temperature";
        filename = "temperature.csv";
    }

    @Override
    public void variationValeurCapteur(float[] values, long time) {
        saveInFile(time+";"+values[0]);
    }
}

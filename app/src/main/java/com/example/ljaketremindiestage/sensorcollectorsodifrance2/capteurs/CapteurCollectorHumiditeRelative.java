package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorHumiditeRelative extends CapteurCollector {
    public CapteurCollectorHumiditeRelative(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_RELATIVE_HUMIDITY;
        name = "Humidite relative";
        filename = "relative_humidity.csv";
    }

    @Override
    public void variationValeurCapteur(float[] values, long time) {
        saveInFile(time+";"+values[0]);
    }
}

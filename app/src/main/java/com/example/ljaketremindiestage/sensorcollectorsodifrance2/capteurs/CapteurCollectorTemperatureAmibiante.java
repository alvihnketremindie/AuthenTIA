package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorTemperatureAmibiante extends CapteurCollector {
    public CapteurCollectorTemperatureAmibiante(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_AMBIENT_TEMPERATURE;
        name = "Temperature ambiante";
        filename = "relative_humidity.csv";
    }

    @Override
    public void variationValeurCapteur(float[] values, long time) {
        saveInFile(time+";"+values[0]);
    }
}

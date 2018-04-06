package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorGyroscope extends CapteurCollector {
    public CapteurCollectorGyroscope(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_GYROSCOPE;
        name = "Gyroscope";
        filename = "gyroscope.csv";
    }

    @Override
    public void variationValeurCapteur(float[] values, long time) {
        saveInFile(time+";"+values[0]+";"+values[1]+";"+values[2]);
    }
}

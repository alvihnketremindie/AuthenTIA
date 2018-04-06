package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorAccelerationLineaire extends CapteurCollector {
    public CapteurCollectorAccelerationLineaire(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_LINEAR_ACCELERATION;
        name = "Acceleration lineaire";
        filename = "linear_acceleration.csv";
    }

    @Override
    public void variationValeurCapteur(float[] values, long time) {
        saveInFile(time+";"+values[0]+";"+values[1]+";"+values[2]);
    }
}

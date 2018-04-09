package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorAccelerationLineaire extends CapteurCollectorDefault {
    public CapteurCollectorAccelerationLineaire(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_LINEAR_ACCELERATION;
        name = "Acceleration lineaire";
        filename = "linear_acceleration.csv";
    }
}

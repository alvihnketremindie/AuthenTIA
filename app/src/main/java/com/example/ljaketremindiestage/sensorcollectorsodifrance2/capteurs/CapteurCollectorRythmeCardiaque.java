package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorRythmeCardiaque extends CapteurCollectorDefault {
    public CapteurCollectorRythmeCardiaque(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_HEART_RATE;
        name = "Rythme cardiaque";
        filename = "heart_rate.csv";
    }
}

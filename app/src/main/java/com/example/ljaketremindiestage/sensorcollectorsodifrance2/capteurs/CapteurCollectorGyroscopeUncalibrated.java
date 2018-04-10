package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorGyroscopeUncalibrated extends CapteurCollectorDefault {
    public CapteurCollectorGyroscopeUncalibrated(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_GYROSCOPE_UNCALIBRATED;
        name = "Gyroscope non calibre";
        filename = "gyroscope_uncalibrated.csv";
    }
}

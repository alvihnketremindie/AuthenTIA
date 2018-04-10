package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorAccelerometreUncalibrated extends CapteurCollectorDefault {
    public CapteurCollectorAccelerometreUncalibrated(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_ACCELEROMETER_UNCALIBRATED;
        name = "Accelerometre non calibre";
        filename = "accelerometer_uncalibrated.csv";
    }
}

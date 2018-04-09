package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorMagneticUncalibrated extends CapteurCollectorMagnetic {
    public CapteurCollectorMagneticUncalibrated(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED;
        name = "Champ magnetique non calibre";
        filename = "magnetic_field_uncalibrated.csv";
    }
}

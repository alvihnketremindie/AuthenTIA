package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorDetecteurDeStationnement extends CapteurCollectorDefault {
    public CapteurCollectorDetecteurDeStationnement(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_STATIONARY_DETECT;
        name = "Detecteur de stationement";
        filename = "stationary_detect.csv";
    }
}

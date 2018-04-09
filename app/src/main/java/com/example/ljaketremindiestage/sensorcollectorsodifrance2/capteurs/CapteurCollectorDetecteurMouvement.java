package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorDetecteurMouvement extends CapteurCollectorDefault {
    public CapteurCollectorDetecteurMouvement(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_MOTION_DETECT;
        name = "Detecteur de mouvement";
        filename = "motion_detect.csv";
    }
}

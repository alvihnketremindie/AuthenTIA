package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorDetecteurPas extends CapteurCollectorDefault {
    public CapteurCollectorDetecteurPas(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_STEP_DETECTOR;
        name = "Detecteur de pas";
        filename = "step_detector.csv";
    }
}

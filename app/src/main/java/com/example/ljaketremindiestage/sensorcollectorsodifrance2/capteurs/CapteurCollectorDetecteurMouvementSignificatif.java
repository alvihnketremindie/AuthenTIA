package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorDetecteurMouvementSignificatif extends CapteurCollectorDefault {
    public CapteurCollectorDetecteurMouvementSignificatif(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_SIGNIFICANT_MOTION;
        name = "Detecteur de mouvement significatif";
        filename = "significant_motion.csv";
    }
}

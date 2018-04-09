package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorBattementDeCoeur extends CapteurCollectorDefault {
    public CapteurCollectorBattementDeCoeur(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_HEART_BEAT;
        name = "Battement de coeur";
        filename = "heart_beat.csv";
    }
}

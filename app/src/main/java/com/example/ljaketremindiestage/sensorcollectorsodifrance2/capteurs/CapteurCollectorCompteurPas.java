package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorCompteurPas extends CapteurCollectorDefault {
    public CapteurCollectorCompteurPas(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_STEP_COUNTER;
        name = "Compteur de pas";
        filename = "step_counter.csv";
    }
}

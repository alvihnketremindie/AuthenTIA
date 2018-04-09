package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorLowLatencyOffBodyDetect extends CapteurCollectorDefault {
    public CapteurCollectorLowLatencyOffBodyDetect(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT;
        name = "Detection de faible temps hors du corps";
        filename = "low_latency_offbody_detect.csv";
    }
}

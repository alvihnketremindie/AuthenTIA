package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorOrientation extends CapteurCollector {
    public CapteurCollectorOrientation(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_ORIENTATION;
        name = "Orientation";
        filename = "orientation.csv";
    }

    @Override
    public void variationValeurCapteur(float[] values, long time) {
        saveInFile(time+";"+values[0]+";"+values[1]+";"+values[2]);
    }
}

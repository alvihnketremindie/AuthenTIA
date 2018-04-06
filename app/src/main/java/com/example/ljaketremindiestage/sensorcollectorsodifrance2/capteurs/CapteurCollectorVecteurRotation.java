package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorVecteurRotation extends CapteurCollector {
    public CapteurCollectorVecteurRotation(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_ROTATION_VECTOR;
        name = "Vecteur de rotation";
        filename = "rotation_vector.csv";
    }

    @Override
    public void variationValeurCapteur(float[] values, long time) {
        saveInFile(time+";"+values[0]+";"+values[1]+";"+values[2]);
    }
}

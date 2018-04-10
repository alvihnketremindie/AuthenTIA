package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorVecteurRotationGeomagnetic extends CapteurCollectorDefault {
    public CapteurCollectorVecteurRotationGeomagnetic(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR;
        name = "Vecteur de rotation geomagnetic";
        filename = "geomagnetic_rotation_vector.csv";
    }
}

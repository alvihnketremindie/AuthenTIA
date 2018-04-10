package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorVecteurRotationPourJeu extends CapteurCollectorDefault {
    public CapteurCollectorVecteurRotationPourJeu(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_GAME_ROTATION_VECTOR;
        name = "Vecteur de rotation pour jeu";
        filename = "game_rotation_vector.csv";
    }
}

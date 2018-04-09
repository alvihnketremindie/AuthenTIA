package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorPose6DOF extends CapteurCollectorDefault {
    public CapteurCollectorPose6DOF(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_POSE_6DOF;
        name = "POSE_6DOF";
        filename = "pose_6dof.csv";
    }
}

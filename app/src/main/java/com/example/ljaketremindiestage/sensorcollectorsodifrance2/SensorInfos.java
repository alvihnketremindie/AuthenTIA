package com.example.ljaketremindiestage.sensorcollectorsodifrance2;

import android.hardware.Sensor;

public class SensorInfos {
    private String name;
    private int typeInt;
    private String type;
    private int version;
    private float resolution;
    private float power;
    private String vendor;
    private float maximumRange;
    private int minimumDelay;

    public SensorInfos(Sensor sensor) {
        name = sensor.getName();
        typeInt = sensor.getType();
        version = sensor.getVersion();
        resolution = sensor.getResolution();
        power = sensor.getPower();
        vendor = sensor.getVendor();
        maximumRange = sensor.getMaximumRange();
        minimumDelay = sensor.getMinDelay();
        type = getStrType();
    }

    private String getStrType() {
        String strType;
        switch (typeInt) {
            case Sensor.TYPE_ACCELEROMETER:
                strType = "ACCELEROMETRE";
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                strType = "CHAMP MAGNETIQUE";
                break;
            case Sensor.TYPE_ORIENTATION:
                strType = "ORIENTATION";
                break;
            case Sensor.TYPE_GYROSCOPE:
                strType = "GYROSCOPE";
                break;
            case Sensor.TYPE_LIGHT:
                strType = "LUMINOSITE";
                break;
            case Sensor.TYPE_PRESSURE:
                strType = "PRESSION";
                break;
            case Sensor.TYPE_TEMPERATURE:
                strType = "TEMPERATURE";
                break;
            case Sensor.TYPE_PROXIMITY:
                strType = "PROXIMITE";
                break;
            case Sensor.TYPE_GRAVITY:
                strType = "GRAVITE";
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                strType = "ACCELERATION LINEAIRE";
                break;
            case Sensor.TYPE_ROTATION_VECTOR:
                strType = "ROTATION VECTORIELLE";
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                strType = "HUMIDITE RELATIVE";
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                strType = "TEMPERATURE AMBIENTE";
                break;
            case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                strType = "TYPE_MAGNETIC_FIELD_UNCALIBRATED";
                break;
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                strType = "TYPE_GAME_ROTATION_VECTOR";
                break;
            case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                strType = "TYPE_GYROSCOPE_UNCALIBRATED";
                break;
            case Sensor.TYPE_SIGNIFICANT_MOTION:
                strType = "TYPE_SIGNIFICANT_MOTION";
                break;
            case Sensor.TYPE_STEP_DETECTOR:
                strType = "TYPE_STEP_DETECTOR";
                break;
            case Sensor.TYPE_STEP_COUNTER:
                strType = "TYPE_STEP_COUNTER";
                break;
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                strType = "TYPE_GEOMAGNETIC_ROTATION_VECTOR";
                break;
            case Sensor.TYPE_HEART_RATE:
                strType = "TYPE_HEART_RATE";
                break;
            case Sensor.TYPE_POSE_6DOF:
                strType = "TYPE_POSE_6DOF";
                break;
            case Sensor.TYPE_STATIONARY_DETECT:
                strType = "TYPE_STATIONARY_DETECT";
                break;
            case Sensor.TYPE_MOTION_DETECT:
                strType = "TYPE_MOTION_DETECT";
                break;
            case Sensor.TYPE_HEART_BEAT:
                strType = "TYPE_HEART_BEAT";
                break;
            case Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT:
                strType = "TYPE_LOW_LATENCY_OFFBODY_DETECT";
                break;
            case Sensor.TYPE_ACCELEROMETER_UNCALIBRATED:
                strType = "TYPE_ACCELEROMETER_UNCALIBRATED";
                break;
            case Sensor.TYPE_ALL:
                strType = "TOUS";
                break;
            default:
                strType = "INCONNU";
                break;
        }
        return strType;
    }

    @Override
    public String toString() {
        return "Informations sur le capteur {" + "\r\n" +
                "\t" + "Nom : " + name + "\r\n" +
                "\t" + "Type : " + typeInt + "\t" + type + "\r\n" +
                "\t" + "Version : " + version + "\r\n" +
                "\t" + "Resolution : " + resolution + "\r\n" +
                "\t" + "Consomation electrique (mA) : " + power + "\r\n" +
                "\t" + "Vendor : " + vendor + "\r\n" +
                "\t" + "Portée : " + maximumRange + "\r\n" +
                "\t" + "Délai minimum : " + minimumDelay + "\r\n" +
                '}';
    }
}

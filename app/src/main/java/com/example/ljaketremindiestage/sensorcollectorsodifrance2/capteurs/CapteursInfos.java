package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

import java.util.Map;

public class CapteursInfos {

    public Sensor sensor;
    public String typeName;
    public String fileName;

    public CapteursInfos(Sensor sensor, Map<String, String> map) {
        this.sensor = sensor;
        this.typeName = map.get("type");
        this.fileName = map.get("filename");
    }
}

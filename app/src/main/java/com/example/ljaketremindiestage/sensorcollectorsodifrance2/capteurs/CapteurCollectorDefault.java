package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorDefault extends CapteurCollector {

    public CapteurCollectorDefault(Sensor sensor) {
        super(sensor);
        type = -1;
        name = sensor.getName().replaceAll("\\s", "");
        filename = name+".csv";
    }

    @Override
    public void variationValeurCapteur(float[] values, long time) {
        String text = time+"";
        int sizetab = values.length;
        for(int i=0;i<sizetab;i++){
            text += ";"+values[i];
        }
        saveInFile(text);
    }
}

package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;

public class CapteurCollectorMagnetic extends CapteurCollector {
    public CapteurCollectorMagnetic(Sensor sensor) {
        super(sensor);
        type = Sensor.TYPE_MAGNETIC_FIELD;
        name = "Champ magnetique";
        filename = "magnetic_field.csv";
    }

    @Override
    public void variationValeurCapteur(float[] values, long time) {
        float x = values[0];
        float y = values[1];
        float z = values[2];
        double norme = Math.sqrt((double) (x*x + y*y + z*z));
        saveInFile(time+";"+x+";"+y+";"+z+";"+norme);
    }
}

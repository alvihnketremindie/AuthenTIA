package com.example.ljaketremindiestage.sensorcollectorsodifrance2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollector;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.logger.DataInternalFileStorage;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.utils.CapteursUtils;

import java.util.ArrayList;
import java.util.List;

public class CollectorActivity extends AppCompatActivity {

    // The sensor manager
    SensorManager sensorManager;
    TextView textview;

    List<CapteurCollector> capteurCollectorList = new ArrayList<CapteurCollector>();

    List<Sensor> sensorList;
    DataInternalFileStorage dataInternalFileStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = findViewById(R.id.textview);
        // Instanicer le SensorManager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // Faire la liste des capteurs de l'appareil
        dataInternalFileStorage = new DataInternalFileStorage(this);
        listSensor();
    }

    @Override
    protected void onResume() {
        super.onResume();
        for(CapteurCollector capteurCollector : capteurCollectorList) {
            sensorManager.registerListener(capteurCollector, capteurCollector.getSensor(), SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        for(CapteurCollector capteurCollector : capteurCollectorList) {
            sensorManager.unregisterListener(capteurCollector, capteurCollector.getSensor());
        }

    }

    /**
     * Lister les capteurs existant
     * <p>
     * Trouve la liste de tous les capteurs existants, trouve un capteur spécifique ou l'ensemble des capteurs d'un type fixé.
     */
    private void listSensor() {
        // Trouver tous les capteurs de l'appareil :
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        // La chaîne descriptive de chaque capteur
        StringBuffer sensorDesc = new StringBuffer();
        CapteurCollector capteurCollector;
        // pour chaque capteur trouvé, construire sa chaîne descriptive
        for (Sensor sensor : sensorList) {
            capteurCollector = CapteursUtils.getCapeursCollectorBySensor(sensor);
            capteurCollector.setDataInternalFileStorage(dataInternalFileStorage);
            capteurCollectorList.add(capteurCollector);
        }
        sensorDesc.append("sensor detected's numbers : " + sensorList.size());
        textview.setText(sensorDesc.toString());
    }
}

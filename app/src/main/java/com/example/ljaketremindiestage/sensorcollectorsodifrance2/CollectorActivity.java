package com.example.ljaketremindiestage.sensorcollectorsodifrance2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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
    String pathdir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = findViewById(R.id.textview);
        // Instanicer le SensorManager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // Faire la liste des capteurs de l'appareil
        dataInternalFileStorage = new DataInternalFileStorage(this);
        /*String rootPath = Environment.getExternalStorageDirectory().getPath().toString();
        File rootPathFile = new File(rootPath);
        rootPathFile.mkdir();
        pathdir = rootPath + "/Android/data/" + getPackageName() + "/files";
        */
        pathdir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
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
            //Log.d("debutcoll", sensor.toString());
            if(capteurCollector != null) {
                capteurCollector.setDataInternalFileStorage(dataInternalFileStorage);
                capteurCollector.setPathdir(pathdir);
                capteurCollectorList.add(capteurCollector);
            }
        }
        sensorDesc.append("sensor detected's numbers : " + sensorList.size());
        textview.setText(sensorDesc.toString());
    }
}

package com.example.ljaketremindiestage.sensorcollectorsodifrance2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ljaketremindiestage.sensorcollectorsodifrance2.utils.CapteursUtils;

public class CollectorActivity extends AppCompatActivity {
    /*
    // The sensor manager
    SensorManager sensorManager;


    List<CapteurCollector> capteurCollectorList = new ArrayList<CapteurCollector>();

    List<Sensor> sensorList;
    DataInternalFileStorage dataInternalFileStorage;
    String pathdir;
    */

    private Context mContext = CollectorActivity.this;
    private Activity mActivity = (Activity) mContext;
    private static final int REQUEST = 112;
    String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.RECEIVE_BOOT_COMPLETED};

    TextView textview;
    Button startButton;
    Button stopButton;

    boolean permissionsOK = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifPermission();
        textview = findViewById(R.id.textview);
        startButton = findViewById(R.id.startService);
        stopButton = findViewById(R.id.stopService);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(CollectorActivity.this, CollectorService.class);
                startIntent.setAction(CapteursUtils.STARTFOREGROUND_ACTION);
                startService(startIntent);
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(CollectorActivity.this, CollectorService.class);
                startIntent.setAction(CapteursUtils.STOPFOREGROUND_ACTION);
                startService(startIntent);
            }
        });


        //listSensor();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
        for (CapteurCollector capteurCollector : capteurCollectorList) {
            sensorManager.registerListener(capteurCollector, capteurCollector.getSensor(), SensorManager.SENSOR_DELAY_NORMAL);
        }
        */
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*
        for (CapteurCollector capteurCollector : capteurCollectorList) {
            sensorManager.unregisterListener(capteurCollector, capteurCollector.getSensor());
        }
        */

    }

    /**
     * Lister les capteurs existant
     * <p>
     * Trouve la liste de tous les capteurs existants, trouve un capteur spécifique ou l'ensemble des capteurs d'un type fixé.
     */

    private void listSensor() {
        // Instanicer le SensorManager
        /*
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // Faire la liste des capteurs de l'appareil
        dataInternalFileStorage = new DataInternalFileStorage(this);
        */
        /*
        String rootPath = Environment.getExternalStorageDirectory().getPath().toString();
        File rootPathFile = new File(rootPath);
        rootPathFile.mkdir();
        pathdir = rootPath + "/Android/data/" + getPackageName() + "/files";
        */
        /*
        pathdir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        // Trouver tous les capteurs de l'appareil :
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        // La chaîne descriptive de chaque capteur
        StringBuffer sensorDesc = new StringBuffer();
        CapteurCollector capteurCollector;
        // pour chaque capteur trouvé, construire sa chaîne descriptive
        for (Sensor sensor : sensorList) {
            capteurCollector = CapteursUtils.getCapeursCollectorBySensor(sensor);
            //Log.d("debutcoll", sensor.toString());
            if (capteurCollector != null) {
                //capteurCollector.setDataInternalFileStorage(dataInternalFileStorage);
                //capteurCollector.setPathdir(pathdir);
                capteurCollectorList.add(capteurCollector);
            }
        }
        sensorDesc.append("Nombre de capteurs détectés : " + capteurCollectorList.size());
        textview.setText(sensorDesc.toString());
        */
    }

    private void verifPermission() {
        if (!CapteursUtils.hasPermissions(mContext, PERMISSIONS)) {
            CapteursUtils.askPermission(mActivity, PERMISSIONS, REQUEST);
        } else {
            permissionsOK = true;
        }
    }
}

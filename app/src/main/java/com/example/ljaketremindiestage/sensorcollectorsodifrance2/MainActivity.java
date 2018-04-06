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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // The sensor manager
    SensorManager sensorManager;
    TextView textview;

    Sensor accelerometre;
    Sensor gravite;
    Sensor accelerationLineaire;
    Sensor luminosite;
    Sensor proximite;
    Sensor magnetisme;
    Sensor gyroscope;

    List<Sensor> sensorList;

    // En attribut de la classe (de l'activité), le seul qui connaisse l'orientation de l'appareil
    private Display mDisplay;

    final SensorEventListener mSensorEventListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Que faire en cas de changement de précision ?
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            // Que faire en cas d'évènements sur le capteur ?
            int capteur = sensorEvent.sensor.getType();
            long instant = sensorEvent.timestamp;
            float valeurs[] = sensorEvent.values;
            float x, y, z;
            String filename = "inconnu";
            String message = ";;;;;";
            if (capteur == Sensor.TYPE_ACCELEROMETER || capteur == Sensor.TYPE_LINEAR_ACCELERATION || capteur == Sensor.TYPE_GRAVITY) {
                switch (mDisplay.getRotation()) {
                    case Surface.ROTATION_0:
                        x = valeurs[0];
                        y = valeurs[1];
                        break;
                    case Surface.ROTATION_90:
                        x = -valeurs[1];
                        y = valeurs[0];
                        break;
                    case Surface.ROTATION_180:
                        x = -valeurs[0];
                        y = -valeurs[1];
                        break;
                    case Surface.ROTATION_270:
                        x = valeurs[1];
                        y = -valeurs[0];
                        break;
                    default:
                        x = 0.0f;
                        y = 0.0f;
                        break;
                }
                // la valeur de z
                z = valeurs[2];

                switch (capteur){
                    case Sensor.TYPE_ACCELEROMETER:
                        filename = "accelerometre";
                        break;
                    case Sensor.TYPE_LINEAR_ACCELERATION:
                        filename = "acceleration lineaire";
                        break;
                    case Sensor.TYPE_GRAVITY:
                        filename = "gravite";
                        break;
                }
                message = instant + ";" + x + ";" + y + ";" + z;
            } else if (capteur == Sensor.TYPE_LIGHT) {
                filename = "luminosite";
                message = instant + ";" + valeurs[0];
            } else if (capteur == Sensor.TYPE_PROXIMITY) {
                filename = "proximite";
                message = instant + ";" + valeurs[0];
            } else if(capteur == Sensor.TYPE_MAGNETIC_FIELD) {
                //Valeurs du vecteur electromagnetique
                x = valeurs[0];
                y = valeurs[1];
                z = valeurs[2];
                // Valeur de la norme de ce vecteur
                double norme = Math.sqrt((double) (x*x + y*y + z*z));
                message = instant + ";" + x + ";" + y + ";" + z+";"+norme;
            } else if (capteur == Sensor.TYPE_GYROSCOPE){
                x = valeurs[0];
                y = valeurs[1];
                z = valeurs[2];
                message = instant + ";" + x + ";" + y + ";" + z;
            }
            Log.d(filename, message);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getString(R.string.app_name);
        textview = findViewById(R.id.textview);
        // Instanicer le SensorManager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // Faire la liste des capteurs de l'appareil
        listSensor();
        mDisplay = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activerCapeur(Sensor.TYPE_ACCELEROMETER, accelerometre);
        activerCapeur(Sensor.TYPE_GRAVITY, gravite);
        activerCapeur(Sensor.TYPE_LINEAR_ACCELERATION, accelerationLineaire);
        activerCapeur(Sensor.TYPE_LIGHT, luminosite);
        activerCapeur(Sensor.TYPE_PROXIMITY, proximite);
        activerCapeur(Sensor.TYPE_MAGNETIC_FIELD, magnetisme);
        activerCapeur(Sensor.TYPE_GYROSCOPE, gyroscope);
    }

    @Override
    protected void onPause() {
        super.onPause();
        desactiverCapteur(accelerometre);
        desactiverCapteur(gravite);
        desactiverCapteur(accelerationLineaire);
        desactiverCapteur(luminosite);
        desactiverCapteur(proximite);
        desactiverCapteur(magnetisme);
        desactiverCapteur(gyroscope);
    }

    protected void activerCapeur(int type, Sensor sensor) {
        sensor = sensorManager.getDefaultSensor(type);
        if (sensor != null) {
            sensorManager.registerListener(mSensorEventListener, sensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    protected void desactiverCapteur (Sensor sensor) {
        if(sensor != null) {
            sensorManager.unregisterListener(mSensorEventListener, sensor);
        }
    }
    /**
     * Lister les capteurs existant
     * <p>
     * Trouve la liste de tous les capteurs existants, trouve un capteur spécifique ou l'ensemble des capteurs d'un type fixé.
     */
    private void listSensor() {
        // Trouver tous les capteurs de l'appareil :
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        // La chaîne descriptive de chaque capteur
        StringBuffer sensorDesc = new StringBuffer();
        SensorInfos sensorInfos;
        // pour chaque capteur trouvé, construire sa chaîne descriptive
        for (Sensor sensor : sensors) {
            sensorInfos = new SensorInfos(sensor);
            sensorDesc.append("New sensor detected : \r\n");
            sensorDesc.append(sensorInfos.toString());
            sensorDesc.append("\r\n---------------------------\r\n");
        }

        sensorDesc.append("sensor detected's numbers : " + sensors.size());
        // Faire quelque chose de cette liste
        //Toast.makeText(this, sensorDesc.toString(), Toast.LENGTH_LONG).show();

        // Pour trouver un capteur spécifique&#160;:
        Sensor gyroscopeDefault = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        // Pour trouver tous les capteurs d'un type fixé :
        List<Sensor> gyroscopes = sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE);

        textview.setText(sensorDesc.toString());
    }
}

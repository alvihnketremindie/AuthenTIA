package com.example.ljaketremindiestage.sensorcollectorsodifrance2.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollector;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorAccelerationLineaire;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorAccelerometre;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorAccelerometreUncalibrated;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorBattementDeCoeur;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorCompteurPas;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorDefault;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorDetecteurDeStationnement;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorDetecteurMouvement;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorDetecteurMouvementSignificatif;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorDetecteurPas;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorGravite;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorGyroscope;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorGyroscopeUncalibrated;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorHumiditeRelative;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorLowLatencyOffBodyDetect;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorLuminosite;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorMagnetic;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorMagneticUncalibrated;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorOrientation;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorPose6DOF;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorPression;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorProximite;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorRythmeCardiaque;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorTemperature;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorTemperatureAmibiante;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorVecteurRotation;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorVecteurRotationGeomagnetic;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.CapteurCollectorVecteurRotationPourJeu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CapteursUtils {

    private static final String LOG_TAG = "DirectoryCreation";
    public static String PACKAGE_NAME = "com.example.ljaketremindiestage.sensorcollectorsodifrance2";
    public static String STARTFOREGROUND_ACTION = PACKAGE_NAME+".startforeground";
    public static String STOPFOREGROUND_ACTION = PACKAGE_NAME+".stopforeground";
    public static String MAIN_ACTION = PACKAGE_NAME+".main";
    public static String PREV_ACTION =  PACKAGE_NAME+".prev";
    public static String PLAY_ACTION =  PACKAGE_NAME+".play";
    public static String NEXT_ACTION =  PACKAGE_NAME+".next";
    public static int FOREGROUND_SERVICE = 101;

    //public static SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmmss");
    public static SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");

    public static String getStrType(int typeInt) {
        return  getCapeursInfosMapByType(typeInt).get("filename");
    }

    public static Map<String, String> getCapeursInfosMapByType(int typeInt) {
        Map<String, String> map = new HashMap<String, String>();



        return map;
    }

    public static int daysBetween(Calendar day1, Calendar day2) {
        Calendar dayOne = (Calendar) day1.clone(),
                dayTwo = (Calendar) day2.clone();

        if (dayOne.get(Calendar.YEAR) == dayTwo.get(Calendar.YEAR)) {
            return Math.abs(dayOne.get(Calendar.DAY_OF_YEAR) - dayTwo.get(Calendar.DAY_OF_YEAR));
        } else {
            if (dayTwo.get(Calendar.YEAR) > dayOne.get(Calendar.YEAR)) {
                //swap them
                Calendar temp = dayOne;
                dayOne = dayTwo;
                dayTwo = temp;
            }
            int extraDays = 0;

            int dayOneOriginalYearDays = dayOne.get(Calendar.DAY_OF_YEAR);

            while (dayOne.get(Calendar.YEAR) > dayTwo.get(Calendar.YEAR)) {
                dayOne.add(Calendar.YEAR, -1);
                // getActualMaximum() important for leap years
                extraDays += dayOne.getActualMaximum(Calendar.DAY_OF_YEAR);
            }

            return Math.abs(extraDays - dayTwo.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays);
        }
    }

    /**
     * Retourne l'objet CapteurCollector associé à un capteur donné.
     * Si le capteur est dans la liste. Sinon la valeur null est retourné
     *
     * @param sensor (Capteur à observer)
     * @return (Objet CapteurCollector associé)
     */

    public static CapteurCollector getCapeursCollectorBySensor(Sensor sensor) {
        CapteurCollector capteurCollector = null;
        switch (sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                capteurCollector = new CapteurCollectorAccelerometre(sensor);
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                capteurCollector = new CapteurCollectorMagnetic(sensor);
                break;
            case Sensor.TYPE_ORIENTATION:
                capteurCollector = new CapteurCollectorOrientation(sensor);
                break;
            case Sensor.TYPE_GYROSCOPE:
                capteurCollector = new CapteurCollectorGyroscope(sensor);
                break;
            case Sensor.TYPE_LIGHT:
                capteurCollector = new CapteurCollectorLuminosite(sensor);
                break;
            case Sensor.TYPE_PRESSURE:
                capteurCollector = new CapteurCollectorPression(sensor);
                break;
            case Sensor.TYPE_TEMPERATURE:
                capteurCollector = new CapteurCollectorTemperature(sensor);
                break;
            case Sensor.TYPE_PROXIMITY:
                capteurCollector = new CapteurCollectorProximite(sensor);
                break;
            case Sensor.TYPE_GRAVITY:
                capteurCollector = new CapteurCollectorGravite(sensor);
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                capteurCollector = new CapteurCollectorAccelerationLineaire(sensor);
                break;
            case Sensor.TYPE_ROTATION_VECTOR:
                capteurCollector = new CapteurCollectorVecteurRotation(sensor);
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                capteurCollector = new CapteurCollectorHumiditeRelative(sensor);
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                capteurCollector = new CapteurCollectorTemperatureAmibiante(sensor);
                break;
            case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                ;
                capteurCollector = new CapteurCollectorMagneticUncalibrated(sensor);
                break;
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                capteurCollector = new CapteurCollectorVecteurRotationPourJeu(sensor);
                break;
            case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                capteurCollector = new CapteurCollectorGyroscopeUncalibrated(sensor);
                break;
            case Sensor.TYPE_SIGNIFICANT_MOTION:
                capteurCollector = new CapteurCollectorDetecteurMouvementSignificatif(sensor);
                break;
            case Sensor.TYPE_STEP_DETECTOR:
                capteurCollector = new CapteurCollectorDetecteurPas(sensor);
                break;
            case Sensor.TYPE_STEP_COUNTER:
                capteurCollector = new CapteurCollectorCompteurPas(sensor);
                break;
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                capteurCollector = new CapteurCollectorVecteurRotationGeomagnetic(sensor);
                break;
            case Sensor.TYPE_HEART_RATE:
                capteurCollector = new CapteurCollectorRythmeCardiaque(sensor);
                break;
                /*
            case Sensor.TYPE_TILT_DETECTOR:
                capteurCollector = new CapteurCollectorDefault(sensor);
                break;
            case Sensor.TYPE_WAKE_GESTURE:
                capteurCollector = new CapteurCollectorDefault(sensor);
                break;
            case Sensor.TYPE_GLANCE_GESTURE:
                capteurCollector = new CapteurCollectorDefault(sensor);
                break;
            case Sensor.TYPE_PICK_UP_GESTURE:
                capteurCollector = new CapteurCollectorDefault(sensor);
                break;
            case Sensor.TYPE_WRIST_TILT_GESTURE:
                capteurCollector = new CapteurCollectorDefault(sensor);
                break;
            case Sensor.TYPE_DEVICE_ORIENTATION:
                capteurCollector = new CapteurCollectorDefault(sensor);
                break;
                */
            case Sensor.TYPE_POSE_6DOF:
                capteurCollector = new CapteurCollectorPose6DOF(sensor);
                break;
            case Sensor.TYPE_STATIONARY_DETECT:
                capteurCollector = new CapteurCollectorDetecteurDeStationnement(sensor);
                break;
            case Sensor.TYPE_MOTION_DETECT:
                capteurCollector = new CapteurCollectorDetecteurMouvement(sensor);
                break;
            case Sensor.TYPE_HEART_BEAT:
                capteurCollector = new CapteurCollectorBattementDeCoeur(sensor);
                break;
                /*
            case Sensor.TYPE_DYNAMIC_SENSOR_META:
                capteurCollector = new CapteurCollectorDefault(sensor);
                break;
                */
            case Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT:
                capteurCollector = new CapteurCollectorLowLatencyOffBodyDetect(sensor);
                break;
            case Sensor.TYPE_ACCELEROMETER_UNCALIBRATED:
                capteurCollector = new CapteurCollectorAccelerometreUncalibrated(sensor);
                break;
            default:
                capteurCollector = new CapteurCollectorDefault(sensor);
                break;
        }
        return capteurCollector;
    }

    /**
     * Méthode pour ecrire du texte (donnees) au sein d'un fichier (filename)
     * Le retour est un booleen qui indique si l'écriture s'est bien passée
     * @param filename
     * @param donnees
     * @return
     */
    public static boolean ecrire(String filename, String donnees, Context context) {
        boolean bool = true;
        FileOutputStream fOut = null;
        OutputStreamWriter osw = null;
        try{
            fOut = context.openFileOutput(filename, Context.MODE_APPEND);
            osw = new OutputStreamWriter(fOut);
            osw.write(donnees);
            osw.flush();
        }
        catch (Exception e) {
            bool = false;
        }
        finally {
            try {
                osw.close();
                fOut.close();
            } catch (IOException e) {
                bool = false;
            }
        }
        return bool;
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void askPermission(Activity activity, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public static File getPublicFileStorageDir(String fileName) {
        // Get the directory for the user's public documents directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), fileName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Repertoire non cree");
        }
        return file;
    }

    public static File getPrivateFileStorageDir(Context context, String fileName) {
        // Get the directory for the app's private documents directory.
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Repertoire non cree");
        }
        return file;
    }
}

package com.example.ljaketremindiestage.sensorcollectorsodifrance2.utils;

import android.content.Context;
import android.hardware.Sensor;

import com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class CapteursUtils {

    public static String getStrType(int typeInt) {
        return  getCapeursInfosMapByType(typeInt).get("filename");
    }

    public static String getCapteurDataFilename(int typeInt) {
        return  getCapeursInfosMapByType(typeInt).get("filename");
    }

    public static Map<String, String> getCapeursInfosMapByType(int typeInt) {
        Map<String, String> map = new HashMap<String, String>();



        return map;
    }

    public static CapteurCollector getCapeursCollectorBySensor(Sensor sensor) {
        Map<String, String> map = new HashMap<String, String>();
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
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                capteurCollector = new CapteurCollectorTemperatureAmibiante(sensor);
                break;
            case Sensor.TYPE_LIGHT:
                capteurCollector = new CapteurCollectorLuminosite(sensor);
                break;
            case Sensor.TYPE_PRESSURE:
                capteurCollector = new CapteurCollectorPression(sensor);
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                capteurCollector = new CapteurCollectorHumiditeRelative(sensor);
                break;
            case Sensor.TYPE_TEMPERATURE:
                capteurCollector = new CapteurCollectorTemperature(sensor);
                break;
            case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                map.put("type", "Champ magnetique non calibre");
                map.put("filename", "magnetic_field_uncalibrated.csv");
                capteurCollector = new CapteurCollectorDefault(sensor);
                break;
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                map.put("type", "Vecteur de rotation pour jeu");
                map.put("filename", "game_rotation_vector.csv");
                capteurCollector = new CapteurCollectorDefault(sensor);
                break;
            case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                map.put("type", "Gyroscope non calibre");
                map.put("filename", "gyroscope_uncalibrated.csv");
                capteurCollector = new CapteurCollectorDefault(sensor);
                break;
            case Sensor.TYPE_SIGNIFICANT_MOTION:
                map.put("type", "Mouvement significatif");
                map.put("filename", "significant_motion.csv");
                capteurCollector = new CapteurCollectorDefault(sensor);
                //capteurCollector = new CapteurCollectorDetecteurMouvementSignificatif(sensor);
                break;
            case Sensor.TYPE_MOTION_DETECT:
                map.put("type", "Detection de mouvement");
                map.put("filename", "motion_detect.csv");
                capteurCollector = new CapteurCollectorDefault(sensor);
                //capteurCollector = new CapteurCollectorDetectionMouvement(sensor);
                break;
            case Sensor.TYPE_STEP_DETECTOR:
                map.put("type", "Detecteur de pas");
                map.put("filename", "step_detector.csv");
                capteurCollector = new CapteurCollectorDefault(sensor);
                //capteurCollector = new CapteurCollectorDetecteurPas(sensor);
                break;
            case Sensor.TYPE_STEP_COUNTER:
                map.put("type", "Compteur de pas");
                map.put("filename", "step_counter.csv");
                capteurCollector = new CapteurCollectorDefault(sensor);
                //capteurCollector = new CapteurCollectorCompteurPas(sensor);
                break;
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                map.put("type", "Vecteur de rotation geomagnetique");
                map.put("filename", "geomagnetic_rotation_vector.csv");
                capteurCollector = new CapteurCollectorDefault(sensor);
                break;
            case Sensor.TYPE_POSE_6DOF:
                map.put("type", "POSE_6DOF");
                map.put("filename", "pose_6dof.csv");
                capteurCollector = new CapteurCollectorDefault(sensor);
                break;
            case Sensor.TYPE_STATIONARY_DETECT:
                map.put("type", "Detection d'arret");
                map.put("filename", "stationary_detect.csv");
                capteurCollector = new CapteurCollectorDefault(sensor);
                //capteurCollector = new CapteurCollectorDetectionArret(sensor);
                break;
            case Sensor.TYPE_HEART_BEAT:
                map.put("type", "Battement de coeur");
                map.put("filename", "heart_beat.csv");
                capteurCollector = new CapteurCollectorDefault(sensor);
                //capteurCollector = new CapteurCollectorBattementDeCoeur(sensor);
                break;
            case Sensor.TYPE_HEART_RATE:
                map.put("type", "Rythme cardiaque");
                map.put("filename", "heart_rate.csv");
                capteurCollector = new CapteurCollectorDefault(sensor);
                //capteurCollector = new CapteurCollectorRythmeCardiaque(sensor);
                break;
            case Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT:
                map.put("type", "Detection de faible hors du corps");
                map.put("filename", "low_latency_offbody_detect.csv");
                capteurCollector = new CapteurCollectorDefault(sensor);
                break;
            case Sensor.TYPE_ACCELEROMETER_UNCALIBRATED:
                map.put("type", "Inconnu");
                map.put("filename", "accelerometer_uncalibrated.csv");
                capteurCollector = new CapteurCollectorDefault(sensor);
                break;
            case Sensor.TYPE_ALL:
                capteurCollector = new CapteurCollectorDefault(sensor);
                break;
            default:
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
}

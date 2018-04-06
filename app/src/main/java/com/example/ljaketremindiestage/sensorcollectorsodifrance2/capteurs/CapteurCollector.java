package com.example.ljaketremindiestage.sensorcollectorsodifrance2.capteurs;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Environment;

import com.example.ljaketremindiestage.sensorcollectorsodifrance2.donnees.CapteursData;
import com.example.ljaketremindiestage.sensorcollectorsodifrance2.logger.DataInternalFileStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

abstract public class CapteurCollector implements SensorEventListener {

    protected Sensor sensor;
    protected int type;
    protected String name;
    protected String filename;

    protected DataInternalFileStorage dataInternalFileStorage;

    protected CapteursData previousCapteursData = null;

    protected File mFile = null;
    protected String pathdir;

    public CapteurCollector(Sensor sensor) {
        this.sensor = sensor;
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = new float[event.values.length];
        System.arraycopy(event.values, 0, values, 0, event.values.length);
        long time = event.timestamp;
        CapteursData capteursData = new CapteursData(name, values, time);
        if(!capteursData.equals(previousCapteursData)) {
            previousCapteursData = capteursData;
            variationValeurCapteur(values, time);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * Cette methode est appele a chaque fois que les valeurs du capteurs change
     * @param values
     * @param time
     */
    public abstract void variationValeurCapteur(float[] values, long time);

    public void setDataInternalFileStorage(DataInternalFileStorage dataInternalFileStorage) {
        this.dataInternalFileStorage = dataInternalFileStorage;
    }

    public void setPathdir(String pathdir) {
        this.pathdir = pathdir.trim();
    }

    public void saveInFile(String toEnreg) {
        /*
        dataInternalFileStorage.ecrire(filename, toEnreg+"\r\n");
        Log.d(filename,toEnreg);
        */
        saveInFileExternal(toEnreg);
    }

    public void saveInFileExternal(String toEnreg) {
        // Si le fichier est lisible et qu'on peut écrire dedans
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                && !Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())) {
            //mFile = new File(pathdir + filename);
            File newFile =  new File(pathdir);
            mFile = new File(pathdir +"/"+ filename.trim());

            OutputStreamWriter output;
            try {
                // On crée un nouveau fichier. Si le fichier existe déjà, il ne sera pas créé
                newFile.mkdir();
                //Log.d(filename, pathdir);
                mFile.createNewFile();
                output = new OutputStreamWriter(new FileOutputStream(mFile, true));
                output.append(toEnreg+"\r\n");
                if(output != null) {
                    output.close();
                    //Log.d(filename,toEnreg);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public Sensor getSensor() {
        return sensor;
    }
}

package com.sodifrance.sensordatacollector;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by nabed.stage on 10/04/2018.
 */

public class CreateCSVFile {


    public static void writeToFileCSV(String sensor,long date, float listData[], Context context, boolean oui) throws FileNotFoundException {
        File myFile = new File(Environment.getExternalStorageDirectory() +
                File.separator + "DataSensor" +
                File.separator + "Data",sensor+".csv"); //on déclare notre futur fichier

        File myDir = new File(Environment.getExternalStorageDirectory() +
                File.separator + "DataSensor" +
                File.separator + "Data"); //pour créer le repertoire dans lequel on va mettre notre fichier

        Boolean success=true;
           if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                && !Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())) {
            if (!myDir.exists()) {
                success = myDir.mkdir(); //On crée le répertoire (s'il n'existe pas!!)
            }
            if (success){
                String data;
                data = String.valueOf(date)+ ";";
                if (listData.length>0){
                    for(int i=0; i<listData.length; i++){
                        if(i==(listData.length-1))
                            data = data + listData[i]+'\n';
                        else
                            data = data + listData[i] + ";";
                    }
                }
                FileOutputStream output = null; //le true est pour écrire en fin de fichier, et non l'écraser
                try {
                    output = new FileOutputStream(myFile,oui);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    output.write(data.getBytes());
                    //Log.d("nassim", data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {Log.d("nassim","ERROR DE CREATION DE DOSSIER");}}

    }

}

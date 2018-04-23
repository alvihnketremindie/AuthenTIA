package com.sodifrance.sensordatacollector;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by nabed.stage on 10/04/2018.
 */

public class zipCSV {

    static final int BUFFER = 2048;

    public static boolean fileZipped(String date){
        File myDir = new File(Environment.getExternalStorageDirectory() +
                File.separator + "DataSensor" +
                File.separator + date + ".zip");
        if (myDir.exists()) {
            //Log.d("nassim", "Le fichier zip existe");
            return true;
        }
        else {
            //Log.d("nassim", "Le fichier zip n'existe pas");
            return false;
        }
    }

    public static String getDateZIP(){
        Calendar datejour = Calendar.getInstance();
        datejour.setTimeInMillis(System.currentTimeMillis()- 86400000);
        String dateZip = "Y"+ String.valueOf(datejour.get(Calendar.YEAR));
        dateZip = dateZip + "M" + String.valueOf(datejour.get(Calendar.MONTH));
        dateZip = dateZip + "D" +String.valueOf(datejour.get(Calendar.DAY_OF_MONTH));
        //Log.d("nassim", "dateZip = "+dateZip);
        return dateZip;
    }




    public static void zip(String namefile) {
        String pathDirectory = (Environment.getExternalStorageDirectory() +
                File.separator + "DataSensor");

        BufferedInputStream origin = null;
        FileOutputStream dest = null;
        try {
            dest = new FileOutputStream(pathDirectory + File.separator +namefile+".zip");
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
            byte data[] = new byte[BUFFER];
            File f = new File(pathDirectory + File.separator +"data"+ File.separator +".");
            String files[] = f.list();
            for (int i=0; i<files.length; i++) {
                FileInputStream fi = new FileInputStream(pathDirectory + File.separator +"data"+ File.separator + files[i]);
                origin = new BufferedInputStream(fi, BUFFER);
                ZipEntry entry = new ZipEntry(files[i]);
                out.putNextEntry(entry);
                int count;
                while((count = origin.read(data, 0, BUFFER)) != -1) {
                    out.write(data, 0, count);
                }
                origin.close();
                Log.d("nassim", "Zip fichier "+files[i]);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

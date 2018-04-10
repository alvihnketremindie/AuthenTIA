package com.example.ljaketremindiestage.sensorcollectorsodifrance2.manager;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.ljaketremindiestage.sensorcollectorsodifrance2.utils.CapteursUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class LogInFileManager {
    public static final String TAG = LogInFileManager.class.getSimpleName();

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public File getPublicFileStorageDir(String dirName) {
        // Get the directory for the user's public documents directory.
        return createNewDirectory(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), dirName);
    }

    public File getPrivateFileStorageDir(Context context, String dirName) {
        // Get the directory for the app's private documents directory.
        return createNewDirectory(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), dirName);
    }

    public File createNewDirectory(File dirFile, String dirName) {
        File file = new File(dirFile, dirName);
        file.mkdirs();
        return file;
    }

    public File createNewFile(File dirFile, String fileName) {
        File file = new File(dirFile, fileName);
        try {
            file.createNewFile();
            //saveInZIPFile(file);
        } catch (IOException e) {
            Log.e(TAG, "Erreur lors de la création du fichier " + fileName);
            file = null;
            //e.printStackTrace();
        }
        return file;
    }

    public void writeInFile(File fileName, String content) {
        /*
        new Thread(new Runnable() {
            public void run() {
                //launchSensorCollection();
            }
        }).start();
        */
        OutputStreamWriter output;
        try {
            // On crée un nouveau fichier. Si le fichier existe déjà, il ne sera pas créé
            output = new OutputStreamWriter(new FileOutputStream(fileName, true));
            output.append(content + "\r\n");
            if (output != null) {
                output.close();
                //Log.d(TAG, content);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveInFileExternal(String fileName, String toEnreg) {
        // Si le fichier est lisible et qu'on peut écrire dedans
        if (isExternalStorageReadable() && isExternalStorageWritable()) {
            //File repertoireCateur = getPublicFileStorageDir("capteurs");
            File fichierCapteurs = createNewFile(getPublicFileStorageDir("capteurs"), fileName);
            if (fichierCapteurs != null) {
                writeInFile(fichierCapteurs, toEnreg);
            }
        }
    }

    private void saveInZIPFile(File fichier) {
        String zipName = fichier.getAbsoluteFile().getParent() + File.separator + "20180410" + ".zip";
        Log.d(TAG, "zipName = " + zipName);
        Log.d(TAG, "absolutePath = " + fichier.getAbsolutePath());
        int BUFFER = 1024;
        try {
            //String name = CapteursUtils.formater.format(new Date());
            FileOutputStream fos = new FileOutputStream(zipName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            byte data[] = new byte[BUFFER];
            //*
            zos.setMethod(ZipOutputStream.DEFLATED);
            zos.setLevel(Deflater.BEST_COMPRESSION);
            //*/

            FileInputStream fis = new FileInputStream(fichier.getAbsolutePath());
            BufferedInputStream origin = new BufferedInputStream(fis, BUFFER);
            /*
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            //*/
            ZipEntry entry = new ZipEntry(fichier.getName());
            //entry.setTime(fichier.lastModified());
            zos.putNextEntry(entry);

            int count;
            while ((count = origin.read(data, 0, BUFFER)) > 0) {
                zos.write(data, 0, count);
            }
            origin.close();

            //Close the input stream
            zos.closeEntry();
            fis.close();
            zos.close();
        } catch (FileNotFoundException fileNotFound) {

        } catch (IOException io) {

        }
    }

    public boolean mustArchiveFile(File file) {
        boolean toReturn = false;
        /*
        long lasttime = file.lastModified();
        long actualtime = System.currentTimeMillis();
        long diff = 1523311200000l - 1523311199999l;
        long diff_in_days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        long diff_in_days2 = diff / (1000  * 60 * 60 * 24);
        */
        Calendar cal1 = new GregorianCalendar();
        cal1.setTimeInMillis(System.currentTimeMillis());
        Calendar cal2 = new GregorianCalendar();
        cal1.setTimeInMillis(file.lastModified());
        int nDays = CapteursUtils.daysBetween(cal1, cal2);
        if (nDays > 0) {
            toReturn = true;
        }
        Log.d(TAG, "Fichier à archiver :  vieux de  " + nDays + " jours");
        return toReturn;
    }
}
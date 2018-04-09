package com.example.ljaketremindiestage.sensorcollectorsodifrance2.manager;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

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
        } catch (IOException e) {
            Log.e(TAG, "Erreur lors de la création du fichier " + fileName);
            file = null;
            //e.printStackTrace();
        }
        return file;
    }

    public void writeInFile(File fileName, String content) {
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
            File repertoireCateur = getPublicFileStorageDir("capteurs");
            if (repertoireCateur != null) {
                File fichierCapteurs = createNewFile(repertoireCateur, fileName);
                if (fichierCapteurs != null) {
                    writeInFile(fichierCapteurs, toEnreg);
                }
            }
        }
    }


}

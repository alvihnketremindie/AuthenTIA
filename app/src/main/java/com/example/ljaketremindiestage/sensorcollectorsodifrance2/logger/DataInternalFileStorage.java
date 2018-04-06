package com.example.ljaketremindiestage.sensorcollectorsodifrance2.logger;

import android.content.Context;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_APPEND;

public class DataInternalFileStorage {
    FileOutputStream fOut = null;
    OutputStreamWriter osw = null;
    private Context context;
    //private String filename;

    public DataInternalFileStorage(Context context) {
        this.context = context;
        //this.filename = filename;
    }

    /**
     * Méthode pour ecrire du texte (donnees) au sein d'un fichier (filename)
     * Le retour est un booleen qui indique si l'écriture s'est bien passée
     * @param filename
     * @param donnees
     * @return
     */
    public boolean ecrire(String filename, String donnees) {
        boolean bool = true;
        try{
            fOut = context.openFileOutput(filename, Context.MODE_APPEND);
            osw = new OutputStreamWriter(fOut);
            osw.write(donnees);
            osw.flush();

            //afficherChemin(filename);
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

    public void afficherChemin(String filename) {
        String file = context.getFileStreamPath(filename).getAbsolutePath();
        Log.d("AffichageCheminFichier", file);
    }
}

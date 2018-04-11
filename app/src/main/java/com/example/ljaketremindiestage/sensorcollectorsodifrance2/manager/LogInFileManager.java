package com.example.ljaketremindiestage.sensorcollectorsodifrance2.manager;

import android.os.Environment;
import android.util.Log;

import com.example.ljaketremindiestage.sensorcollectorsodifrance2.utils.CapteursUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class LogInFileManager {
    public static final String TAG = LogInFileManager.class.getSimpleName();

    /**
     * Checks if external storage is available for read and write
     *
     * @return TRUE if OK, FALSE otherwise
     */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }


    /**
     * Checks if external storage is available to at least read
     *
     * @return TRUE if OK, FALSE otherwise
     */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
    /*
    public File getFichiersCapteurs(String dirName, String fileName)  {
        File fichier;
        File dossier_parent = new File(dirName);
        if(!(dossier_parent.exists() && dossier_parent.isDirectory())) {
            dossier_parent.mkdirs();
            Log.d(CapteursUtils.APP_NAME, "Création du dossier " + dossier_parent.getAbsolutePath());
        }
        fichier = new File(dossier_parent, fileName);
        if(!fichier.exists()) {
            try {
                fichier.createNewFile();
                Log.d(CapteursUtils.APP_NAME, "Création du fichier " + fichier.getAbsolutePath());
            } catch (IOException e) {
                Log.e(TAG, "Erreur lors de la création du fichier " + fileName);
                fichier = null;
                e.printStackTrace();
            }
        }
        return fichier;
    }


    public File getPublicFileStorageDir(String dirName) {
        // Get the directory for the user's public documents directory.
        return createNewDirectory(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), dirName);
    }

    public File getPrivateFileStorageDir(Context context, String dirName) {
        // Get the directory for the app's private documents directory.
        return createNewDirectory(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), dirName);
    }
    //*/

    public File retourneDossier(String dirName) {
        File dirFile = new File(dirName);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
            Log.d(CapteursUtils.APP_NAME, "Création du dossier " + dirFile.getAbsolutePath());
        }
        if (!dirFile.isDirectory()) {
            Log.e(CapteursUtils.APP_NAME, "Erreur " + dirName + " existe mais n'est pas un dossier");
            dirFile = null;
        }
        return dirFile;
    }

    public File retourneFicher(File dirFile, String fileName) {
        if (dirFile == null) return null;
        File fichier = new File(dirFile, fileName);
        if (!fichier.exists()) {
            try {
                fichier.createNewFile();
                Log.d(CapteursUtils.APP_NAME, "Création du fichier " + fichier.getAbsolutePath());
            } catch (IOException e) {
                Log.e(TAG, "Erreur lors de la création du fichier " + fileName);
                fichier = null;
                e.printStackTrace();
            }
        }
        return fichier;
    }

    public File retourneFichier(String dirName, String fileName) {
        return retourneFicher(retourneDossier(dirName), fileName);
    }

    public void writeInFile(File fileName, String content) {
        OutputStreamWriter output;
        try {
            // On crée un nouveau fichier. Si le fichier existe déjà, il ne sera pas créé
            output = new OutputStreamWriter(new FileOutputStream(fileName, true));
            output.append(content + "\r\n");
            if (output != null) {
                output.close();
                //Log.d(fileName.getName(), content);
            }
        } catch (FileNotFoundException e) {
            Log.e(CapteursUtils.APP_NAME, "Le fichier " + fileName.getAbsolutePath() + " n'a pu être trouvé");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(CapteursUtils.APP_NAME, "Erreur sur les entrées sorties. ==> Problèmes d'écriture");
            e.printStackTrace();
        }
    }

    public void saveInFileExternal(String fileName, String toEnreg) {
        // Si le fichier est lisible et qu'on peut écrire dedans
        if (isExternalStorageReadable() && isExternalStorageWritable()) {
            //File repertoireCateur = getPublicFileStorageDir("capteurs");
            //File fichierCapteurs = createNewFile(getPublicFileStorageDir("capteurs"), fileName);
            File fichierCapteurs = retourneFichier(CapteursUtils.APP_PATH_SENSOR, fileName);
            if (fichierCapteurs != null) {
                if (mustArchiveFile(fichierCapteurs)) {
                    zip(CapteursUtils.formater.format(new Date()));
                }
                writeInFile(fichierCapteurs, toEnreg);
            }
        } else {
            Log.e(CapteursUtils.APP_NAME, "L'autorisation d'écriture n'a pas été accordée");
        }
    }

    public void zip(String zipDate) {
        File zipDir = retourneDossier(CapteursUtils.APP_PATH_SENSOR_ARCHIVE);
        File zipFile = new File(zipDir, "data_" + zipDate + ".zip");
        if (zipFile != null && !zipFile.exists()) {

            BufferedInputStream bufferedInputStream = null;
            FileOutputStream fileOutputStream = null;
            try {
                zipFile.createNewFile();
                Log.d(CapteursUtils.APP_NAME, "Création du fichier ZIP : " + zipFile.getAbsolutePath());
                fileOutputStream = new FileOutputStream(zipFile.getAbsolutePath());
                ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(fileOutputStream));
                byte data[] = new byte[CapteursUtils.BUFFER];
                File f = new File(CapteursUtils.APP_PATH_SENSOR);
                File files[] = f.listFiles();
                for (File file : files) {
                    if (!file.isDirectory()) {
                        FileInputStream fileInputStream = new FileInputStream(file);
                        bufferedInputStream = new BufferedInputStream(fileInputStream, CapteursUtils.BUFFER);
                        ZipEntry zipEntry = new ZipEntry(file.getName());
                        zipOutputStream.putNextEntry(zipEntry);
                        int count;
                        while ((count = bufferedInputStream.read(data, 0, CapteursUtils.BUFFER)) != -1) {
                            zipOutputStream.write(data, 0, count);
                        }
                        bufferedInputStream.close();
                        Log.d(CapteursUtils.APP_NAME, "Compression du fichier : " + file.getAbsolutePath());
                        //Vidage du fichier sauvegarder
                        FileOutputStream fileToEmpty = new FileOutputStream(file.getAbsolutePath());
                        fileToEmpty.close();
                        Log.d(CapteursUtils.APP_NAME, "Vidage du fichier : " + file.getAbsolutePath());
                    }
                }
                zipOutputStream.close();
            } catch (FileNotFoundException e) {
                Log.e(CapteursUtils.APP_NAME, "Un des fichiers n'a pu être retrouvé");
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(CapteursUtils.APP_NAME, "Une erreur s'est produite lors de la lecture / ecriture dans les fichiers");
                e.printStackTrace();
            } catch (Exception e) {
                Log.e(CapteursUtils.APP_NAME, "Une erreur innatendu s'est produite lors du zip");
                e.printStackTrace();
            }
        } else if (zipFile == null) {
            Log.e(CapteursUtils.APP_NAME, "Le fichier zip instancier est null");
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
            Log.d(TAG, "Fichier à archiver :  vieux de  " + nDays + " jours");
        }
        return toReturn;
        //return true;
    }
}
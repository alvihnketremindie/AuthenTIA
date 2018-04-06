package com.example.ljaketremindiestage.sensorcollectorsodifrance2.donnees;

import android.util.Log;

import java.util.Arrays;

/**
 * Cette classe represente les données envoyés par un capteurs
 */
public class CapteursData {
    private long timestamp;
    private String source;
    private float[] values;

    public CapteursData() {
        this(null, null, System.currentTimeMillis());
    }

    public CapteursData(float[] values) {
        this(null, values, System.currentTimeMillis());
    }

    public CapteursData(String source, float[] values) {
        this(source, values, System.currentTimeMillis());
    }

    public CapteursData(String source, float[] values, long timestamp) {
        this.source = source;
        this.values = values;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CapteursData that = (CapteursData) o;
        return this.getSource().equals(that.getSource()) && Arrays.equals(this.getValues(), that.getValues());
    }

    /**
     * Getter & Setter
     */
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public float[] getValues() {
        return values;
    }

    public void setValues(float[] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        String text = "";
        int sizetab = getValues().length;
        for(int i=0;i<sizetab;i++){
            text += ";"+values[i];
        }
        return getTimestamp() + text+"\r\n";
    }

    public void logger() {
        Log.d(this.source, this.toString());
    }
}

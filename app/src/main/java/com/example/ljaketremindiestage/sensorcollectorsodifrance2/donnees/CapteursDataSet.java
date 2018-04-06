package com.example.ljaketremindiestage.sensorcollectorsodifrance2.donnees;

import com.example.ljaketremindiestage.sensorcollectorsodifrance2.logger.DataInternalFileStorage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class CapteursDataSet implements Serializable {

    public static final int CAPACITY_UNLIMITED = -1;
    public static final int CAPACITY_DEFAULT = 500;

    private int type;
    private String source;
    private List<CapteursData> dataList;
    private int capacity;
    private CapteursData previousCapteursData = null;

    public CapteursDataSet() {
        dataList = new ArrayList<>();
        capacity = CAPACITY_DEFAULT;
    }

    public CapteursDataSet(CapteursDataSet dataBatch) {
        type = dataBatch.getType();
        source = dataBatch.getSource();
        dataList = new ArrayList<>(dataBatch.getDataList().size());
        dataList.addAll(dataBatch.getDataList());
        capacity = dataBatch.capacity;
    }

    public CapteursDataSet(List<CapteursData> dataList) {
        this();
        this.dataList = dataList;
    }

    public CapteursDataSet(String source) {
        this();
        this.source = source;
    }

    private void trimDataToCapacity() {
        // check if there's a capacity limit
        if (capacity == CAPACITY_UNLIMITED) {
            return;
        }

        // check if trimming is needed
        if (dataList == null || dataList.size() < capacity) {
            return;
        }

        // remove oldest data
        while (dataList.size() > capacity) {
            dataList.remove(0);
        }
    }

    public void roundToDecimalPlaces(int decimalPlaces) {
        for (int dataIndex = 0; dataIndex < dataList.size(); dataIndex++) {
            CapteursData data = dataList.get(dataIndex);
            for (int dimension = 0; dimension < data.getValues().length; dimension++) {
                data.getValues()[dimension] = roundToDecimalPlaces(data.getValues()[dimension], decimalPlaces);
            }
        }
    }

    public static float roundToDecimalPlaces(float value, int decimalPlaces) {
        double shift = Math.pow(10, decimalPlaces);
        return (float) (Math.round(value * shift) / shift);
    }


    public float getFrequency() {
        CapteursData newest = getNewestData();
        CapteursData oldest = getOldestData();
        if (newest == oldest) {
            return 0;
        }
        long delta = newest.getTimestamp() - oldest.getTimestamp();
        return dataList.size() / (delta / TimeUnit.SECONDS.toMillis(1));
    }

    public void addData(CapteursData capteursData) {
        //*
        //previousCapteursData = getNewestData();
        if(!capteursData.equals(previousCapteursData)) {
            dataList.add(capteursData);
            //trimDataToCapacity();
            capteursData.logger();
            previousCapteursData = capteursData;
        }
        //*/
        /* capteursData.logger(); */
    }

    public void addData(CapteursData capteursData, DataInternalFileStorage dataInternalFileStorage) {
        //*
        //previousCapteursData = getNewestData();
        if(!capteursData.equals(previousCapteursData)) {
            dataList.add(capteursData);
            //trimDataToCapacity();
            previousCapteursData = capteursData;
            if(dataInternalFileStorage.ecrire(capteursData.getSource()+".csv", capteursData.toString())) {
                //capteursData.logger();
            }
        }
        //*/
        /* capteursData.logger(); */
    }

    public void addData(List<CapteursData> capteursDataList) {
        for(CapteursData capteursData:capteursDataList) {
            addData(capteursData);
        }
    }

    public CapteursData getNewestData() {
        if (dataList == null || dataList.size() < 1) {
            return null;
        }
        return dataList.get(dataList.size() - 1);
    }


    public CapteursData getOldestData() {
        if (dataList == null || dataList.size() < 1) {
            return null;
        }
        return dataList.get(0);
    }

    public List<CapteursData> getDataSince(long timestamp) {
        List<CapteursData> dataSince = new ArrayList<>();
        for (int i = dataList.size() - 1; i >= 0; i--) {
            if (dataList.get(i).getTimestamp() > timestamp) {
                dataSince.add(dataList.get(i));
            } else {
                break;
            }
        }
        Collections.reverse(dataSince);
        return dataSince;
    }

    public void removeDataBefore(long timestamp) {
        dataList = getDataSince(timestamp);
    }

    /**
     * Getter & Setter
     */
    public List<CapteursData> getDataList() {
        return dataList;
    }

    public void setDataList(List<CapteursData> dataList) {
        this.dataList = dataList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

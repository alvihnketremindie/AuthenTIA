package com.example.ljaketremindiestage.sensorcollectorsodifrance2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.ljaketremindiestage.sensorcollectorsodifrance2.utils.CapteursUtils;

public class CollectorBroadcastreceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent startIntent = new Intent(context, CollectorService.class);
        startIntent.setAction(CapteursUtils.STARTFOREGROUND_ACTION);
        context.startService(startIntent);
    }
}

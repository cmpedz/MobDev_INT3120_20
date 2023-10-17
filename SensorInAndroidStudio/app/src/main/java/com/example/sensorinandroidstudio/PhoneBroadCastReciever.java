package com.example.sensorinandroidstudio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class PhoneBroadCastReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //nhận số điện thoại đnag gọi đến
        String currentPhoneNumber=intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        Toast.makeText(context,"current phone : "+TelephonyManager.EXTRA_INCOMING_NUMBER,Toast.LENGTH_SHORT).show();

    }
}

package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AirLineAnnoucements extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("air mode :","clicked");
    }
}

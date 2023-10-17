package com.example.sensorinandroidstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;

public class Phone extends AppCompatActivity {
    private PhoneBroadCastReciever phoneManager=new PhoneBroadCastReciever();
    private IntentFilter intentFilter=new IntentFilter("com.example.sensorinandroidstudio");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        Button call=(Button) findViewById(R.id.callbutton);
        registerReceiver(phoneManager,intentFilter);

        Button check=(Button) findViewById(R.id.checkbutton);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneCalling=new Intent("com.example.sensorinandroidstudio");
                sendBroadcast(phoneCalling);
            }
        });


        //gọi điện
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:0829496356"));
                startActivity(intent);
            }
        });

        //gửi tin nhắn
        Button send=(Button)findViewById(R.id.sendbutton);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_SENDTO,Uri.parse("sms:0829496356"));
                intent.putExtra("sms_body","hello world");
                startActivity(intent);
            }
        });
    }
    @Override
    public void onStop(){
        super.onStop();
        unregisterReceiver(phoneManager);
    }
}